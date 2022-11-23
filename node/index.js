const tracer = require("./tracing")("node-service");
const express = require("express");
const { MongoClient } = require("mongodb");

const app = express();
app.use(express.json());
const port = 3000;
let db;

app.get("/node/api/product", async (req, res) => {
  const todos = await db.collection("todos").find({}).toArray();
  res.send(todos);
});

app.get("/node/api/hello", async (req, res) => {
  getData();
  res.send("hello");
});

app.get("/node/api/product/:id", async (req, res) => {
  const todo = await db.collection("todos").findOne({ id: req.params.id });
  res.send(todo);
});

const startServer = () => {
  tracer.startSpan("startServer").end();


  app.listen(port, () => {
    console.log(`Example app listening on port ${port}`);
  });
};

async function getData (){
  try{
        var rp = require ('request-promise-native');
        var options = {
        uri:'http://localhost:8085/todo',
        json:true
      };

      var response = await rp(options);
      return response;
  }catch(error){
      throw error;
  }        
}

try{
  console.log(getData());
}catch(error){
  console.log("er",error);
}
startServer();