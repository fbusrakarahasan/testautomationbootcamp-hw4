<h1 align="center"> :bookmark_tabs: Enuygun - Test Automation Bootcamp - HW4 </h1>
 

> ##  ``` Postman - PetStore API Testi   ``` 
 
https://petstore.swagger.io/#/store/ store path endpointlerin API testinin yazılması.

## ✅ Place an order for a pet 
https://petstore.swagger.io/v2/store/order

Body'e aşağıdaki json verileri girilerek pet siparişi verilir.

```json
{
    "id": 5,
    "petId": 15,
    "quantity": 35,
    "shipDate": "2022-10-05T01:22:06.626Z",
    "status": "avaiable",
    "complete": true
}
``` 
 
<details>
  <summary><h4>Testi görüntülemek için tıklayın</h4></summary>
  
```javascript
var jsonData = pm.response.json(); 
var size = Object.keys(jsonData).length;  
var responsLimit = 5000;
var responseTime = pm.response.responseTime; 

pm.test("Body has all keys", function () {
    pm.expect(pm.response.text()).to.include("id");
    pm.expect(pm.response.text()).to.include("petId");
    pm.expect(pm.response.text()).to.include("quantity");
    pm.expect(pm.response.text()).to.include("shipDate");
    pm.expect(pm.response.text()).to.include("status");
    pm.expect(pm.response.text()).to.include("complete");
}); 

tests["Size control"] = size == 6;     
tests["id type control"] = typeof(jsonData.id) === "number"; 
tests["petId type control"] = typeof(jsonData.petId) === "number"; 
tests["quantity type control"] = typeof(jsonData.quantity) === "number"; 
tests["shipDate type control"] = typeof(jsonData.shipDate) === "string"; 
tests["status type control"] = typeof(jsonData.status) === "string"; 
tests["complete type control"] = typeof(jsonData.complete) === "boolean"; 

pm.test("Response Body Values is correct", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.id).to.eql(5);
    pm.expect(jsonData.petId).to.eql(15);
    pm.expect(jsonData.quantity).to.eql(35);
    pm.expect(jsonData.shipDate).to.eql("2022-10-05T01:22:06.626+0000");
    pm.expect(jsonData.status).to.eql("avaiable");
    pm.expect(jsonData.complete).to.eql(true);
});
 
pm.test("Header is correct", function () {
    pm.response.to.have.header("Content-Type", "application/json");
    pm.response.to.have.header("Transfer-Encoding", "chunked");
    pm.response.to.have.header("Connection", "keep-alive");
    pm.response.to.have.header("Access-Control-Allow-Origin", "*");
    pm.response.to.have.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    pm.response.to.have.header("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");
    pm.response.to.have.header("Server", "Jetty(9.2.9.v20150224)");
});

pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});
     
pm.test("Status code name has string", () => {
  pm.response.to.have.status("OK");
});

pm.test("Response should not be error", function () { 
    pm.response.to.not.be.error; 
});

pm.test("Response must be valid", function(){
    pm.response.to.be.ok;
});

pm.test("Response size is less than 1KB", function () {
    pm.expect(pm.response.responseSize).to.be.below(1024);
});

pm.test("Check response time", () => {  
  if (responseTime > responsLimit) {       
    console.log("Response time was longer than " + responsLimit + " ms " + "(" + responseTime + " ms)" + " / Response Date: " + pm.response.headers.get("Date"));
  }
  pm.expect(responseTime).to.be.below(responsLimit); 
});  
```
</details>

## ✅ Find purchase order by ID
https://petstore.swagger.io/v2/store/order/5

<details>
  <summary><h4>Testi görüntülemek için tıklayın</h4></summary>
  
```javascript
var jsonData = pm.response.json(); 
var size = Object.keys(jsonData).length;  
var responsLimit = 5000;
var responseTime = pm.response.responseTime; 

pm.test("Body has all keys", function () {
    pm.expect(pm.response.text()).to.include("id");
    pm.expect(pm.response.text()).to.include("petId");
    pm.expect(pm.response.text()).to.include("quantity");
    pm.expect(pm.response.text()).to.include("shipDate");
    pm.expect(pm.response.text()).to.include("status");
    pm.expect(pm.response.text()).to.include("complete");
}); 

tests["Size control"] = size == 6;     
tests["id type control"] = typeof(jsonData.id) === "number"; 
tests["petId type control"] = typeof(jsonData.petId) === "number"; 
tests["quantity type control"] = typeof(jsonData.quantity) === "number"; 
tests["shipDate type control"] = typeof(jsonData.shipDate) === "string"; 
tests["status type control"] = typeof(jsonData.status) === "string"; 
tests["complete type control"] = typeof(jsonData.complete) === "boolean"; 

pm.test("Response Body Values is correct", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.id).to.eql(5);
    pm.expect(jsonData.petId).to.eql(15);
    pm.expect(jsonData.quantity).to.eql(35);
    pm.expect(jsonData.shipDate).to.eql("2022-10-05T01:22:06.626+0000");
    pm.expect(jsonData.status).to.eql("avaiable");
    pm.expect(jsonData.complete).to.eql(true);
});
 
pm.test("Header is correct", function () {
    pm.response.to.have.header("Content-Type", "application/json");
    pm.response.to.have.header("Transfer-Encoding", "chunked");
    pm.response.to.have.header("Connection", "keep-alive");
    pm.response.to.have.header("Access-Control-Allow-Origin", "*");
    pm.response.to.have.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    pm.response.to.have.header("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");
    pm.response.to.have.header("Server", "Jetty(9.2.9.v20150224)");
});

pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});
     
pm.test("Status code name has string", () => {
  pm.response.to.have.status("OK");
});

pm.test("Response should not be error", function () { 
    pm.response.to.not.be.error; 
});

pm.test("Response must be valid", function(){
    pm.response.to.be.ok;
});

pm.test("Response size is less than 1KB", function () {
    pm.expect(pm.response.responseSize).to.be.below(1024);
});

pm.test("Check response time", () => {  
  if (responseTime > responsLimit) {       
    console.log("Response time was longer than " + responsLimit + " ms " + "(" + responseTime + " ms)" + " / Response Date: " + pm.response.headers.get("Date"));
  }
  pm.expect(responseTime).to.be.below(responsLimit); 
});  
```
</details>

## ✅ Delete purchase order by ID
https://petstore.swagger.io/v2/store/order/5

<details>
  <summary><h4>Testi görüntülemek için tıklayın</h4></summary>
  
```javascript
var jsonData = pm.response.json(); 
var size = Object.keys(jsonData).length;  
var responsLimit = 5000;
var responseTime = pm.response.responseTime; 

pm.test("Body has all keys", function () {
    pm.expect(pm.response.text()).to.include("code");
    pm.expect(pm.response.text()).to.include("type");
    pm.expect(pm.response.text()).to.include("message"); 
}); 

tests["Size control"] = size == 3;     
tests["id type control"] = typeof(jsonData.code) === "number"; 
tests["petId type control"] = typeof(jsonData.type) === "string"; 
tests["quantity type control"] = typeof(jsonData.message) === "string";  

pm.test("Response Body Values is correct", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.code).to.eql(200);
    pm.expect(jsonData.type).to.eql("unknown");
    pm.expect(jsonData.message).to.eql("5");  
}); 

pm.test("Header is correct", function () {
    pm.response.to.have.header("Content-Type", "application/json");
    pm.response.to.have.header("Transfer-Encoding", "chunked");
    pm.response.to.have.header("Connection", "keep-alive");
    pm.response.to.have.header("Access-Control-Allow-Origin", "*");
    pm.response.to.have.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    pm.response.to.have.header("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");
    pm.response.to.have.header("Server", "Jetty(9.2.9.v20150224)");
});

pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});
     
pm.test("Status code name has string", () => {
  pm.response.to.have.status("OK");
});

pm.test("Response should not be error", function () { 
    pm.response.to.not.be.error; 
});

pm.test("Response must be valid", function(){
    pm.response.to.be.ok;
});

pm.test("Response size is less than 1KB", function () {
    pm.expect(pm.response.responseSize).to.be.below(1024);
});

pm.test("Check response time", () => {  
  if (responseTime > responsLimit) {       
    console.log("Response time was longer than " + responsLimit + " ms " + "(" + responseTime + " ms)" + " / Response Date: " + pm.response.headers.get("Date"));
  }
  pm.expect(responseTime).to.be.below(responsLimit); 
});  
```
</details>

## ✅ Returns pet inventories by status
https://petstore.swagger.io/v2/store/inventory

<details>
  <summary><h4>Testi görüntülemek için tıklayın</h4></summary>
  
```javascript
var jsonData = pm.response.json(); 
var size = Object.keys(jsonData).length;  
var responsLimit = 5000;
var responseTime = pm.response.responseTime; 

pm.test("Body has all keys", function () {
    pm.expect(pm.response.text()).to.include("code");
    pm.expect(pm.response.text()).to.include("type");
    pm.expect(pm.response.text()).to.include("message"); 
}); 

tests["Size control"] = size == 3;     
tests["id type control"] = typeof(jsonData.code) === "number"; 
tests["petId type control"] = typeof(jsonData.type) === "string"; 
tests["quantity type control"] = typeof(jsonData.message) === "string";  

pm.test("Response Body Values is correct", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.code).to.eql(200);
    pm.expect(jsonData.type).to.eql("unknown");
    pm.expect(jsonData.message).to.eql("5");  
}); 

pm.test("Header is correct", function () {
    pm.response.to.have.header("Content-Type", "application/json");
    pm.response.to.have.header("Transfer-Encoding", "chunked");
    pm.response.to.have.header("Connection", "keep-alive");
    pm.response.to.have.header("Access-Control-Allow-Origin", "*");
    pm.response.to.have.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    pm.response.to.have.header("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");
    pm.response.to.have.header("Server", "Jetty(9.2.9.v20150224)");
});

pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});
     
pm.test("Status code name has string", () => {
  pm.response.to.have.status("OK");
});

pm.test("Response should not be error", function () { 
    pm.response.to.not.be.error; 
});

pm.test("Response must be valid", function(){
    pm.response.to.be.ok;
});

pm.test("Response size is less than 1KB", function () {
    pm.expect(pm.response.responseSize).to.be.below(1024);
});

pm.test("Check response time", () => {  
  if (responseTime > responsLimit) {       
    console.log("Response time was longer than " + responsLimit + " ms " + "(" + responseTime + " ms)" + " / Response Date: " + pm.response.headers.get("Date"));
  }
  pm.expect(responseTime).to.be.below(responsLimit); 
});  
```
</details>
