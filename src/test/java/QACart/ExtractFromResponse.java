package QACart;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExtractFromResponse {

    @Test
    public void ExtractResponse_Assertion() {
        String name = given().baseUri("https://61085830d73c6400170d3911.mockapi.io")
                .when().get("/api/v1/users")
                .then().extract().response().path("[0].name");

        // If you are using JsonPath, you don't need to take an object from it.
        //String name = JsonPath.from(res.asString()).getString("[1].name");
        System.out.println(name);

        //String id = res.path("[0].id");

    }
}
