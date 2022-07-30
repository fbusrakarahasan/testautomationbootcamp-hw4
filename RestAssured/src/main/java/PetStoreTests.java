import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.number.OrderingComparison.lessThan;

public class PetStoreTests {

    public PetStoreTests() {
        baseURI = "https://petstore.swagger.io/v2/store";
    }

    @Test
    @Order(1)
    public void PlaceAnOrderForAPet() {
        String requestUrl = String.format("%s/order/", baseURI);

        String postData = "{\n" +
                "    \"id\": 5,\n" +
                "    \"petId\": 15,\n" +
                "    \"quantity\": 35,\n" +
                "    \"shipDate\": \"2022-10-05T01:22:06.626Z\",\n" +
                "    \"status\": \"avaiable\",\n" +
                "    \"complete\": true\n" +
                "}";

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(postData)
                        .when()
                        .post(requestUrl)
                        .then()
                        .statusCode(200)
                        .time(lessThan(4000L))
                        .extract().response();

        Assert.assertNotNull(response.getBody()); // Body null kontrolü

        int id = response.path("id");
        int petId = response.path("petId");
        int quantity = response.path("quantity");
        String shipDate = response.path("shipDate").toString();
        String status = response.path("status").toString();
        boolean complete = response.path("complete");

        Assert.assertEquals(5,id);
        Assert.assertEquals(15,petId);
        Assert.assertEquals(35,quantity);
        Assert.assertEquals("2022-10-05T01:22:06.626+0000",shipDate);
        Assert.assertEquals("avaiable",status);
        Assert.assertTrue(complete);

        Assert.assertEquals(8, response.getHeaders().size()); // Header Key Boyutu
        Assert.assertEquals("[application/json]",response.getHeaders().getValues("Content-Type").toString()); // Content-Type içeriyor mu?
        Assert.assertEquals("[chunked]",response.getHeaders().getValues("Transfer-Encoding").toString()); // Transfer-Encoding içeriyor mu?
        Assert.assertEquals("[keep-alive]",response.getHeaders().getValues("Connection").toString()); // Connection içeriyor mu?
        Assert.assertEquals("[*]",response.getHeaders().getValues("Access-Control-Allow-Origin").toString()); // Access-Control-Allow-Origin içeriyor mu?
        Assert.assertEquals("[GET, POST, DELETE, PUT]",response.getHeaders().getValues("Access-Control-Allow-Methods").toString()); // Access-Control-Allow-Methods içeriyor mu?
        Assert.assertEquals("[Content-Type, api_key, Authorization]",response.getHeaders().getValues("Access-Control-Allow-Headers").toString()); // Access-Control-Allow-Headers içeriyor mu?
        Assert.assertEquals("[Jetty(9.2.9.v20150224)]",response.getHeaders().getValues("Server").toString()); // Server içeriyor mu?
    }

    @Test
    @Order(2)
    public void FindPurchaseOrderByID() {
        String requestUrl = String.format("%s/order/5/", baseURI);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .when()
                        .get(requestUrl)
                        .then()
                        .statusCode(200)
                        .time(lessThan(4000L))
                        .extract().response();


        Assert.assertNotNull(response.getBody()); // Body null kontrolü

        int id = response.path("id");
        int petId = response.path("petId");
        int quantity = response.path("quantity");
        String shipDate = response.path("shipDate").toString();
        String status = response.path("status").toString();
        boolean complete = response.path("complete");

        Assert.assertEquals(5,id);
        Assert.assertEquals(15,petId);
        Assert.assertEquals(35,quantity);
        Assert.assertEquals("2022-10-05T01:22:06.626+0000",shipDate);
        Assert.assertEquals("avaiable",status);
        Assert.assertTrue(complete);

        Assert.assertEquals(8, response.getHeaders().size()); // Header Key Boyutu
        Assert.assertEquals("[application/json]",response.getHeaders().getValues("Content-Type").toString()); // Content-Type içeriyor mu?
        Assert.assertEquals("[chunked]",response.getHeaders().getValues("Transfer-Encoding").toString()); // Transfer-Encoding içeriyor mu?
        Assert.assertEquals("[keep-alive]",response.getHeaders().getValues("Connection").toString()); // Connection içeriyor mu?
        Assert.assertEquals("[*]",response.getHeaders().getValues("Access-Control-Allow-Origin").toString()); // Access-Control-Allow-Origin içeriyor mu?
        Assert.assertEquals("[GET, POST, DELETE, PUT]",response.getHeaders().getValues("Access-Control-Allow-Methods").toString()); // Access-Control-Allow-Methods içeriyor mu?
        Assert.assertEquals("[Content-Type, api_key, Authorization]",response.getHeaders().getValues("Access-Control-Allow-Headers").toString()); // Access-Control-Allow-Headers içeriyor mu?
        Assert.assertEquals("[Jetty(9.2.9.v20150224)]",response.getHeaders().getValues("Server").toString()); // Server içeriyor mu?
    }

    @Test
    @Order(3)
    public void DeletePurchaseOrderByID(){
        String requestUrl = String.format("%s/order/5/", baseURI);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .when()
                        .delete(requestUrl)
                        .then()
                        .statusCode(200)
                        .time(lessThan(4000L))
                        .extract().response();


        Assert.assertNotNull(response.getBody()); // Body null kontrolü

        int code = response.path("code");
        String type = response.path("type");
        String message = response.path("message");

        Assert.assertEquals(200,code);
        Assert.assertEquals("unknown",type);
        Assert.assertEquals("5",message);

        Assert.assertEquals(8, response.getHeaders().size()); // Header Key Boyutu
        Assert.assertEquals("[application/json]",response.getHeaders().getValues("Content-Type").toString()); // Content-Type içeriyor mu?
        Assert.assertEquals("[chunked]",response.getHeaders().getValues("Transfer-Encoding").toString()); // Transfer-Encoding içeriyor mu?
        Assert.assertEquals("[keep-alive]",response.getHeaders().getValues("Connection").toString()); // Connection içeriyor mu?
        Assert.assertEquals("[*]",response.getHeaders().getValues("Access-Control-Allow-Origin").toString()); // Access-Control-Allow-Origin içeriyor mu?
        Assert.assertEquals("[GET, POST, DELETE, PUT]",response.getHeaders().getValues("Access-Control-Allow-Methods").toString()); // Access-Control-Allow-Methods içeriyor mu?
        Assert.assertEquals("[Content-Type, api_key, Authorization]",response.getHeaders().getValues("Access-Control-Allow-Headers").toString()); // Access-Control-Allow-Headers içeriyor mu?
        Assert.assertEquals("[Jetty(9.2.9.v20150224)]",response.getHeaders().getValues("Server").toString()); // Server içeriyor mu?
    }

    @Test
    @Order(4)
    public void ReturnsPetInventoriesByStatus(){
        String requestUrl = String.format("%s/inventory/", baseURI);

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .when()
                        .get(requestUrl)
                        .then()
                        .statusCode(200)
                        .time(lessThan(4000L))
                        .extract().response();


        Assert.assertNotNull(response.getBody()); // Body null kontrolü

        Assert.assertEquals(8, response.getHeaders().size()); // Header Key Boyutu
        Assert.assertEquals("[application/json]",response.getHeaders().getValues("Content-Type").toString()); // Content-Type içeriyor mu?
        Assert.assertEquals("[chunked]",response.getHeaders().getValues("Transfer-Encoding").toString()); // Transfer-Encoding içeriyor mu?
        Assert.assertEquals("[keep-alive]",response.getHeaders().getValues("Connection").toString()); // Connection içeriyor mu?
        Assert.assertEquals("[*]",response.getHeaders().getValues("Access-Control-Allow-Origin").toString()); // Access-Control-Allow-Origin içeriyor mu?
        Assert.assertEquals("[GET, POST, DELETE, PUT]",response.getHeaders().getValues("Access-Control-Allow-Methods").toString()); // Access-Control-Allow-Methods içeriyor mu?
        Assert.assertEquals("[Content-Type, api_key, Authorization]",response.getHeaders().getValues("Access-Control-Allow-Headers").toString()); // Access-Control-Allow-Headers içeriyor mu?
        Assert.assertEquals("[Jetty(9.2.9.v20150224)]",response.getHeaders().getValues("Server").toString()); // Server içeriyor mu?
    }
}