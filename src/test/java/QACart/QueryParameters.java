package QACart;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class QueryParameters {

    @Test
    public void Query_Parameter() {
        given().baseUri("https://todo.qacart.com")
                .queryParam("type" , "VIDEO")
                .log().all()
                .when().get("/api/v1/info/lectures")
                .then().log().all()
                .assertThat().statusCode(200);

    }

    @Test
    public void Query_Params() {
        given().baseUri("https://todo.qacart.com")
                .queryParams("type" , "VIDEO" , "mode" , "PAID")
                .log().all()
                .when().get("/api/v1/info/lectures")
                .then().log().all()
                .assertThat().statusCode(200);

    }

    @Test
    public void HashMapClass_Queries() {
        // HasMap Class
        HashMap<String, String> queries = new HashMap<>();
        queries.put("type" , "VIDEO");
        queries.put("mode" , "PAID");

        given()
                .baseUri("https://todo.qacart.com")
                .params(queries)
                .log().all()
        .when()
                .get("/api/v1/info/courses")
        .then()
                .log().all()
        .assertThat()
                .statusCode(200);
    }
}
