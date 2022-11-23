# code-with-open-trace Project
## Run all images for execution
```shell script
$ docker run -d --name jaeger \
  -e COLLECTOR_ZIPKIN_HOST_PORT=:9411 \
  -e COLLECTOR_OTLP_ENABLED=true \
  -p 6831:6831/udp \
  -p 6832:6832/udp \
  -p 5778:5778 \
  -p 16686:16686 \
  -p 4317:4317 \
  -p 4318:4318 \
  -p 14250:14250 \
  -p 14268:14268 \
  -p 14269:14269 \
  -p 9411:9411 \
  jaegertracing/all-in-one:1.39

```

##   <ins> 1. Spring boot Application for Open Tracing  </ins>
## Add the following dependency for tracing 

```shell script
1. For tracing
implementation 'org.springframework.cloud:spring-cloud-starter-sleuth' \\ for managing tracing

2. For zipkin
implementation 'org.springframework.cloud:spring-cloud-sleuth-zipkin'   \\ for sending traces to collector

3.Database log 
implementation 'p6spy:p6spy:3.9.1'                                      \\ for tracing DB transactions
```
## Apply the configuration

```shell script
***********application.yml*************
spring:
  sleuth:
  propagation:
    type: B3
  jdbc:
    p6spy:
      tracing:
        include-parameter-values: false
  zipkin:
    baseUrl: http://127.0.0.1:9411/

#To view request related logs in console (doesn't work for gateway):
logging:
  level:
    org:
      springframework:
        web:
          servlet:
            DispatcherServlet: DEBUG
```

##   <ins> 2.  Quarkus Application for Open Tracing  </ins>
If you want to learn more about Quarkus open tracing , please visit its website: [https://quarkus.io/guides/opentelemetry](https://quarkus.io/guides/opentelemetry)

## Dependency need to be added to implement the open distributed tracing.
```shell script
** Implement Tracing add the following dependency ***
implementation("io.quarkus:quarkus-opentelemetry")
```

```shell script
** The JDBC instrumentation will add a span for each JDBC queries done by your application, to enable it, add the following dependency   ***
implementation("io.quarkus:quarkus-opentelemetry")
```
```shell script
** OpenTelemetry propagates cross-cutting concerns through propagators that will share an underlying Context for storing state and accessing data across the lifespan of a distributed transaction ***
1. for The b3, b3multi, jaeger and ottrace propagator we need to add following 
implementation("io.opentelemetry:opentelemetry-extension-trace-propagators")

2.The xray propagator will need the aws extension to be added as a dependency
implementation("io.opentelemetry:opentelemetry-extension-aws")
```
### Apply the configuration
```shell script
**************application.properties***********
quarkus.application.name=myservice  
quarkus.opentelemetry.propagators=b3,baggage
quarkus.opentelemetry.enabled=true
quarkus.opentelemetry.tracer.exporter.otlp.endpoint=http://localhost:4317
quarkus.log.console.format=%d{HH:mm:ss} %-5p traceId=%X{traceId}, parentId=%X{parentId}, spanId=%X{spanId}, sampled=%X{sampled} [%c{2.}] (%t) %s%e%n
quarkus.http.access-log.pattern="...traceId=%{X,traceId} spanId=%{X,spanId}"
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew --console=plain quarkusDev
```

## View Tracing Information 
Then visit the [Jaeger UI(http://localhost:16686/)](http://localhost:16686/) to see the tracing information.


## image :
<img src="images/Jaeger_ui.png" width="1000"/>
