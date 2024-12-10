package QACart;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class HeadersInRestAssured {

    @Test
    public void UsingHeader() {
        given().baseUri("https://todo.qacart.com")
                .headers("language", "JAVA")
                .headers("type", "MANUAL")
                .when().get("/api/v1/info/courses")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void UsingHeaders() {
        given().baseUri("https://todo.qacart.com")
                .headers("language", "JAVA", "type", "MANUAL")
                .when().get("/api/v1/info/courses")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void HeaderClass() {
        Header TypeHeader = new Header("type", "WEB");
        Header LanguageHeader = new Header("language", "JAVA");
        given().baseUri("https://todo.qacart.com")
                .header(LanguageHeader)
                .header(TypeHeader)
                .when().get("/api/v1/info/courses")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void HeadersClass() {
        // Preparing the Headers
        Header TypeHeader = new Header("type", "WEB");
        Header LanguageHeader = new Header("language", "JAVA");
        Headers InfoHeaders = new Headers(TypeHeader, LanguageHeader);

        given().baseUri("https://todo.qacart.com")
                .headers(InfoHeaders)
                .when().get("/api/v1/info/courses")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void HashMapClass() {
        // HasMap Class
        HashMap<String, String> InfoHeader = new HashMap<>();
        InfoHeader.put("type" , "WEB");
        InfoHeader.put("language" , "JAVA");
        InfoHeader.put("name" , "SELENIUM");

        given().baseUri("https://todo.qacart.com")
                .headers(InfoHeader)
                .when().get("/api/v1/info/courses")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("count" , equalTo(1));
    }
}
