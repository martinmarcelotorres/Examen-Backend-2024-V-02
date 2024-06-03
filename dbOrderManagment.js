// Conectar a la base de datos
var db = connect("localhost:27017/dbOrderManagment");

// Crea la colección
db.createCollection("orders");

var orderDocument = {
  "clientId": 2,
  "items": [
    {
      "productId": 1,
      "quantity": 1,
      "subtotal": 1200
    },
    {
      "productId": 2,
      "quantity": 2,
      "subtotal": 1600
    }
  ],
  "total": 2800,
  "status": "P",
  "orderDate": new Date("2024-06-03T13:52:51.184Z")
};

// Inserta el documento Order en la colección
db.orders.insertOne(orderDocument);
