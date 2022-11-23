package main
import (
  "context"
  "log"
  "net/http"
  "github.com/gin-gonic/gin"
  "go.mongodb.org/mongo-driver/bson"
  "go.mongodb.org/mongo-driver/mongo"
  "go.mongodb.org/mongo-driver/mongo/options"
  "go.opentelemetry.io/contrib/instrumentation/github.com/gin-gonic/gin/otelgin"
  "go.opentelemetry.io/contrib/instrumentation/go.mongodb.org/mongo-driver/mongo/otelmongo"
  "go.opentelemetry.io/otel"
  "demo/go-trace/tracing"
  "go.opentelemetry.io/contrib/propagators/b3"
)
var client *mongo.Client
func main() {   //Export traces to Jaeger
  tp, tpErr := tracing.JaegerTraceProvider()
  if tpErr != nil {
      log.Fatal(tpErr)
  }
  otel.SetTracerProvider(tp)
  p := b3.New(b3.WithInjectEncoding(b3.B3MultipleHeader | b3.B3SingleHeader))
	otel.SetTextMapPropagator(p)


  connectMongo()
  startWebServer()
}
func connectMongo() {
  opts := options.Client()
  //Mongo OpenTelemetry instrumentation
  opts.Monitor = otelmongo.NewMonitor()
  opts.ApplyURI("mongodb://localhost:27017")
  client, _ = mongo.Connect(context.Background(), opts)
}
func startWebServer() {
  r := gin.Default()
  //gin OpenTelemetry instrumentation
  r.Use(otelgin.Middleware("demo-go"))
  r.GET("/todo", func(c *gin.Context) {
      collection := client.Database("todo").Collection("todos")
      //Make sure to pass c.Request.Context() as the context and not c itself
      cur, findErr := collection.Find(c.Request.Context(), bson.D{})
      if findErr != nil {
          c.AbortWithError(500, findErr)
          return
      }
      results := make([]interface{}, 0)
      curErr := cur.All(c, &results)
      if curErr != nil {
          c.AbortWithError(500, curErr)
          return
      }
      c.JSON(http.StatusOK, results)
  })
  _ = r.Run(":8085")
}