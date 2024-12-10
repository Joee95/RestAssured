package QACart;

import QACart.pojo.LoginPojo;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RequestBody {

    @Test
    public void HashMapClass_Body() {
        // HasMap Class
        HashMap<String, String> body = new HashMap<>();
        body.put("email", "hatem@example.com");
        body.put("password", "123456");

        given()
                .baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(body)
                .log().all()
                .when()
                .get("/api/v1/info/courses")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void ReadFromFile_Body() {

        // Read From File
        File body = new File("src/test/resources/login.json");

        given()
                .baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(body)
                .log().all()
                .when()
                .post("/api/v1/users/login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void PojoClass() {

        LoginPojo body = new LoginPojo();
        body.setEmail("yousef.kenawyy@gmail.com");
        body.setPassword("New.pass.word-1");

        given()
                .baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(body)
                .log().all()
                .when()
                .post("/api/v1/users/login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void URLEncoded() {

        HashMap<String , String> formParam = new HashMap<>();
        formParam.put("FOO" , "1234");

        given()
                .baseUri("https://todo.qacart.com")
                .contentType(ContentType.URLENC)
                .formParams(formParam)
                .log().all()
                .when()
                .post("/api/v1/users/login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void MultiPart() {

        File json = new File("src/test/resources/login.json");

        given()
                .baseUri("https://todo.qacart.com")
                .contentType(ContentType.URLENC)
                .multiPart("file" , json)
                .log().all()
                .when()
                .post("/api/v1/users/login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }


}
