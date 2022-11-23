## Node Poc with opentelementry

1. ode v10+ is supported by OpenTelemetry.

2. Install to following packages for opentelementry
``` 
npm install --save @opentelemetry/api 
npm install --save @opentelemetry/sdk-trace-node
npm install --save opentelemetry-instrumentation-express
npm install --save @opentelemetry/instrumentation-mongodb
npm install --save @opentelemetry/instrumentation-http
npm install --save express
npm install --save mongodb //for poc perpose 
npm install --save @opentelemetry/exporter-jaeger
``` 
#### Add B3 Propogation (Read from : https://www.npmjs.com/package/@opentelemetry/propagator-b3)
``` 
npm i @opentelemetry/propagator-b3
``` 
// after download package add following code in tracing.js 
``` 
const api = require('@opentelemetry/api');
const { CompositePropagator } = require('@opentelemetry/core');
const { B3Propagator, B3InjectEncoding } = require('@opentelemetry/propagator-b3');
api.propagation.setGlobalPropagator(
  new CompositePropagator({
    propagators: [
      new B3Propagator(),
      new B3Propagator({ injectEncoding: B3InjectEncoding.MULTI_HEADER }),
    ],
  })
);

``` 
3. Create Tracing file tracing.js 

4. Run your application and execute a few requests

5. use your browser to view Jaeger UI at  http://localhost:16686/
