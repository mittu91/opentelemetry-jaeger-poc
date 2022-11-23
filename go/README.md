### Steps (Note : for this poc we use mongodb)
## Install packages 
- Install Gin and Mongo-driver 
``` 
    go get -u github.com/gin-gonic/gin 
    go get go.mongodb.org/mongo-driver/mongo
```
- To install the OTel SDK, run:
``` 
    go get go.opentelemetry.io/otel 
    go.opentelemetry.io/otel/sdk 
``` 
- Gin instrumentation: Install otelgin 
``` 
   go get go.opentelemetry.io/contrib/instrumentation/github.com/gin-gonic/gin/otelgin
``` 
- Mongo instrumentation: Install otelmongo  
``` 
   go get go.opentelemetry.io/contrib/instrumentation/go.mongodb.org/mongo-driver/mongo/otelmongo
``` 
- Propagation Configuration
  
``` 
    Install pcakge 
    go get go.opentelemetry.io/contrib/propagators/b3"
    [Single Header: b3: {TraceId}-{SpanId}-{SamplingState}-{ParentSpanId}
   Multiple Headers: x-b3-traceid: {TraceId} x-b3-parentspanid: {ParentSpanId} x-b3-spanid: {SpanId} x-b3-sampled: {SamplingState} x-b3-flags: {DebugFlag}]

    for single header ,
    p := b3.New()\
	// Register the B3 propagator globally.\
	otel.SetTextMapPropagator(p)\

    for single and multiheader\
    p := b3.New(b3.WithInjectEncoding(b3.B3MultipleHeader | b3.B3SingleHeader))\
	otel.SetTextMapPropagator(p)\
``` 
- OpenTelemetry Go and Jaeger Tracing: Export traces to Jaeger (Visualization with Jaeager)\
1) Install the Jaeger exporter 
``` 
    go get go.opentelemetry.io/otel/exporters/jaeger
``` 
2) create tracing file and add following code(tracing.go): 
``` 
package tracing
import (\
  "go.opentelemetry.io/otel/exporters/jaeger"
  "go.opentelemetry.io/otel/sdk/resource"
  sdktrace "go.opentelemetry.io/otel/sdk/trace"
  semconv "go.opentelemetry.io/otel/semconv/v1.4.0"
)
func JaegerTraceProvider() (*sdktrace.TracerProvider, error) {
  exp, err := jaeger.New(jaeger.WithCollectorEndpoint(jaeger.WithEndpoint("http://localhost:14268/api/traces")))
  if err != nil {
      return nil, err
  }
  tp := sdktrace.NewTracerProvider(
      sdktrace.WithBatcher(exp),
      sdktrace.WithResource(resource.NewWithAttributes(
          semconv.SchemaURL,
          semconv.ServiceNameKey.String("do-demo"), // name of the service 
          semconv.DeploymentEnvironmentKey.String("dev"),
      )),
  )
  return tp, nil
}
``` 



