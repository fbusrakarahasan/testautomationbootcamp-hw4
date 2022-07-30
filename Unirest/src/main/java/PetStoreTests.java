import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static io.restassured.RestAssured.baseURI;

public class PetStoreTests {
    HttpHeaders headers;
    RestTemplate restTemplate;

    public PetStoreTests() {
        baseURI = "https://petstore.swagger.io/v2/store";
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate = new RestTemplate();
    }

    @Test
    @Order(1)
    public void PlaceAnOrderForAPet() throws UnirestException {
        String requestUrl = baseURI + "/order/";

        String postData = "{\n" +
                "    \"id\": 5,\n" +
                "    \"petId\": 15,\n" +
                "    \"quantity\": 35,\n" +
                "    \"shipDate\": \"2022-10-05T01:22:06.626Z\",\n" +
                "    \"status\": \"avaiable\",\n" +
                "    \"complete\": true\n" +
                "}";

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.post(requestUrl)
                .header("Content-Type", "application/json")
                .body(postData)
                .asJson();


        Assert.assertEquals(200, response.getStatus()); // Durum Kodu 200?
        Assert.assertEquals("OK", response.getStatusText()); // Durum adı OK?
        Assert.assertNotNull(response.getBody()); // Body null kontrolü

        Assert.assertEquals(8, response.getHeaders().size()); // Header Key Boyutu
        Assert.assertTrue(response.getHeaders().containsKey("Content-Type")); // Content-Type içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Transfer-Encoding")); // Transfer-Encoding içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Connection")); // Connection içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Access-Control-Allow-Origin")); // Access-Control-Allow-Origin içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Access-Control-Allow-Methods")); // Access-Control-Allow-Methods içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Access-Control-Allow-Headers")); // Access-Control-Allow-Headers içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Server")); // Server içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Date")); // Date içeriyor mu?

        Assert.assertEquals("[application/json]", response.getHeaders().get("Content-Type").toString()); // Content-Type verisi doğru mu?
        Assert.assertEquals("[chunked]", response.getHeaders().get("Transfer-Encoding").toString()); // Transfer-Encoding verisi doğru mu?
        Assert.assertEquals("[keep-alive]", response.getHeaders().get("Connection").toString()); // Connection verisi doğru mu?
        Assert.assertEquals("[*]", response.getHeaders().get("Access-Control-Allow-Origin").toString()); // Access-Control-Allow-Origin verisi doğru mu?
        Assert.assertEquals("[GET, POST, DELETE, PUT]", response.getHeaders().get("Access-Control-Allow-Methods").toString()); // Access-Control-Allow-Methods verisi doğru mu?
        Assert.assertEquals("[Content-Type, api_key, Authorization]", response.getHeaders().get("Access-Control-Allow-Headers").toString()); // Access-Control-Allow-Headers verisi doğru mu?
        Assert.assertEquals("[Jetty(9.2.9.v20150224)]", response.getHeaders().get("Server").toString()); // Server verisi doğru mu?

        Assert.assertEquals(5, response.getBody().getObject().get("id")); // Response body ID kontrolü
        Assert.assertEquals(15, response.getBody().getObject().get("petId")); // Response body petId kontrolü
        Assert.assertEquals(35, response.getBody().getObject().get("quantity")); // Response body quantity kontrolü
        Assert.assertEquals("2022-10-05T01:22:06.626+0000", response.getBody().getObject().get("shipDate")); // Response body shipDate kontrolü
        Assert.assertEquals("avaiable", response.getBody().getObject().get("status")); // Response body status kontrolü
        Assert.assertEquals(true, response.getBody().getObject().get("complete")); // Response body complete kontrolü
    }

    @Test
    @Order(2)
    public void FindPurchaseOrderByID() throws UnirestException {
        String requestUrl = baseURI + "/order/5/";

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get(requestUrl)
                .asJson();

        Assert.assertEquals(200, response.getStatus()); // Durum Kodu 200?
        Assert.assertEquals("OK", response.getStatusText()); // Durum adı OK?
        Assert.assertNotNull(response.getBody()); // Body null kontrolü

        Assert.assertEquals(8, response.getHeaders().size()); // Header Key Boyutu
        Assert.assertTrue(response.getHeaders().containsKey("Content-Type")); // Content-Type içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Transfer-Encoding")); // Transfer-Encoding içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Connection")); // Connection içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Access-Control-Allow-Origin")); // Access-Control-Allow-Origin içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Access-Control-Allow-Methods")); // Access-Control-Allow-Methods içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Access-Control-Allow-Headers")); // Access-Control-Allow-Headers içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Server")); // Server içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Date")); // Date içeriyor mu?

        Assert.assertEquals("[application/json]", response.getHeaders().get("Content-Type").toString()); // Content-Type verisi doğru mu?
        Assert.assertEquals("[chunked]", response.getHeaders().get("Transfer-Encoding").toString()); // Transfer-Encoding verisi doğru mu?
        Assert.assertEquals("[keep-alive]", response.getHeaders().get("Connection").toString()); // Connection verisi doğru mu?
        Assert.assertEquals("[*]", response.getHeaders().get("Access-Control-Allow-Origin").toString()); // Access-Control-Allow-Origin verisi doğru mu?
        Assert.assertEquals("[GET, POST, DELETE, PUT]", response.getHeaders().get("Access-Control-Allow-Methods").toString()); // Access-Control-Allow-Methods verisi doğru mu?
        Assert.assertEquals("[Content-Type, api_key, Authorization]", response.getHeaders().get("Access-Control-Allow-Headers").toString()); // Access-Control-Allow-Headers verisi doğru mu?
        Assert.assertEquals("[Jetty(9.2.9.v20150224)]", response.getHeaders().get("Server").toString()); // Server verisi doğru mu?

        Assert.assertEquals(5, response.getBody().getObject().get("id")); // Response body ID kontrolü
        Assert.assertEquals(15, response.getBody().getObject().get("petId")); // Response body petId kontrolü
        Assert.assertEquals(35, response.getBody().getObject().get("quantity")); // Response body quantity kontrolü
        Assert.assertEquals("2022-10-05T01:22:06.626+0000", response.getBody().getObject().get("shipDate")); // Response body shipDate kontrolü
        Assert.assertEquals("avaiable", response.getBody().getObject().get("status")); // Response body status kontrolü
        Assert.assertEquals(true, response.getBody().getObject().get("complete")); // Response body complete kontrolü
    }

    @Test
    @Order(3)
    public void DeletePurchaseOrderByID() throws UnirestException {
        String requestUrl = baseURI + "/order/5/";

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.delete(requestUrl)
                .asJson();

        Assert.assertEquals(200, response.getStatus()); // Durum Kodu 200?
        Assert.assertEquals("OK", response.getStatusText()); // Durum adı OK?
        Assert.assertNotNull(response.getBody()); // Body null kontrolü

        Assert.assertEquals(8, response.getHeaders().size()); // Header Key Boyutu
        Assert.assertTrue(response.getHeaders().containsKey("Content-Type")); // Content-Type içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Transfer-Encoding")); // Transfer-Encoding içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Connection")); // Connection içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Access-Control-Allow-Origin")); // Access-Control-Allow-Origin içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Access-Control-Allow-Methods")); // Access-Control-Allow-Methods içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Access-Control-Allow-Headers")); // Access-Control-Allow-Headers içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Server")); // Server içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Date")); // Date içeriyor mu?

        Assert.assertEquals("[application/json]", response.getHeaders().get("Content-Type").toString()); // Content-Type verisi doğru mu?
        Assert.assertEquals("[chunked]", response.getHeaders().get("Transfer-Encoding").toString()); // Transfer-Encoding verisi doğru mu?
        Assert.assertEquals("[keep-alive]", response.getHeaders().get("Connection").toString()); // Connection verisi doğru mu?
        Assert.assertEquals("[*]", response.getHeaders().get("Access-Control-Allow-Origin").toString()); // Access-Control-Allow-Origin verisi doğru mu?
        Assert.assertEquals("[GET, POST, DELETE, PUT]", response.getHeaders().get("Access-Control-Allow-Methods").toString()); // Access-Control-Allow-Methods verisi doğru mu?
        Assert.assertEquals("[Content-Type, api_key, Authorization]", response.getHeaders().get("Access-Control-Allow-Headers").toString()); // Access-Control-Allow-Headers verisi doğru mu?
        Assert.assertEquals("[Jetty(9.2.9.v20150224)]", response.getHeaders().get("Server").toString()); // Server verisi doğru mu?

        Assert.assertEquals(200, response.getBody().getObject().get("code")); // code kontrolü
        Assert.assertEquals("unknown", response.getBody().getObject().get("type")); // type kontrolü
        Assert.assertEquals("5", response.getBody().getObject().get("message")); // message kontrolü
    }

    @Test
    @Order(4)
    public void ReturnsPetInventoriesByStatus() throws UnirestException {
        String requestUrl = baseURI + "/inventory";

        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.get(requestUrl)
                .asJson();

        Assert.assertEquals(200, response.getStatus()); // Durum Kodu 200?
        Assert.assertEquals("OK", response.getStatusText()); // Durum adı OK?
        Assert.assertNotNull(response.getBody()); // Body null kontrolü

        Assert.assertEquals(8, response.getHeaders().size()); // Header Key Boyutu
        Assert.assertTrue(response.getHeaders().containsKey("Content-Type")); // Content-Type içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Transfer-Encoding")); // Transfer-Encoding içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Connection")); // Connection içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Access-Control-Allow-Origin")); // Access-Control-Allow-Origin içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Access-Control-Allow-Methods")); // Access-Control-Allow-Methods içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Access-Control-Allow-Headers")); // Access-Control-Allow-Headers içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Server")); // Server içeriyor mu?
        Assert.assertTrue(response.getHeaders().containsKey("Date")); // Date içeriyor mu?

        Assert.assertEquals("[application/json]", response.getHeaders().get("Content-Type").toString()); // Content-Type verisi doğru mu?
        Assert.assertEquals("[chunked]", response.getHeaders().get("Transfer-Encoding").toString()); // Transfer-Encoding verisi doğru mu?
        Assert.assertEquals("[keep-alive]", response.getHeaders().get("Connection").toString()); // Connection verisi doğru mu?
        Assert.assertEquals("[*]", response.getHeaders().get("Access-Control-Allow-Origin").toString()); // Access-Control-Allow-Origin verisi doğru mu?
        Assert.assertEquals("[GET, POST, DELETE, PUT]", response.getHeaders().get("Access-Control-Allow-Methods").toString()); // Access-Control-Allow-Methods verisi doğru mu?
        Assert.assertEquals("[Content-Type, api_key, Authorization]", response.getHeaders().get("Access-Control-Allow-Headers").toString()); // Access-Control-Allow-Headers verisi doğru mu?
        Assert.assertEquals("[Jetty(9.2.9.v20150224)]", response.getHeaders().get("Server").toString()); // Server verisi doğru mu?
    }
}