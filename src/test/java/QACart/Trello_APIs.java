package QACart;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Trello_APIs {

    final String Trello_url = "https://api.trello.com/1/boards/";
    final String Key = "Use your own key";
    final String Token = "Use your own token";
    String Name = "Trello_Board_09";
    private static String Board_ID;
    private static String Created_Board_Name;

    @Test
    @Order(1)
    public void CreateBoard() {
        Response response = given()
                .baseUri(Trello_url)
                .contentType(ContentType.JSON)
                .queryParam("name", Name)
                .queryParam("key", Key)
                .queryParam("token", Token)
                .log().all()
                .when()
                .post()
                .then()
                .log().all()
                .extract().response(); // Extract the full response

        // ** Extractions **
        Board_ID = response.jsonPath().getString("id");
        Created_Board_Name = response.jsonPath().getString("name");

        // ** Assertions **
        response.then().statusCode(200);
        response.then().header("Content-Type", containsString("application/json"));
        response.then().time(lessThan(3000L));
        response.then()
                .body("name", equalTo(Name))
                .body("desc", notNullValue())
                .body("closed", notNullValue())
                .body("idOrganization", notNullValue())
                .body("pinned", notNullValue())
                .body("url", notNullValue())
                .body("shortUrl", notNullValue())
                .body("prefs", notNullValue())
                .body("labelNames", notNullValue())
                .body("limits", notNullValue());
    }

    @Test
    @Order(2)
    public void GetBoard() {
        Response response = given()
                .baseUri(Trello_url + Board_ID)
                .contentType(ContentType.JSON)
                .queryParam("key", Key)
                .queryParam("token", Token)
                .log().all()
                .when()
                .get()
                .then()
                .log().all()
                .extract().response(); // Extract the full response

        // Log the response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        // Check for valid JSON response
        if (response.getStatusCode() == 200) {
            String boardId = response.jsonPath().getString("id");
            System.out.println("Board ID: " + boardId);
        } else {
            System.out.println("Error: Received status code " + response.getStatusCode());
        }
    }

    @Test
    @Order(3)
    public void DeleteBoard() {
        Response response = given()
                .baseUri(Trello_url + Board_ID)
                .contentType(ContentType.JSON)
                .queryParam("key", Key)
                .queryParam("token", Token)
                .log().all()
                .when()
                .delete()
                .then()
                .log().all()
                .extract().response(); // Extract the full response

        // Log the response body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        // Check for valid JSON response
        if (response.getStatusCode() == 200) {
            System.out.println("Status Code is 200");
        } else {
            System.out.println("Error: Received status code " + response.getStatusCode());
        }
    }
}
