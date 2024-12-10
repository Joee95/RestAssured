package QACart;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

public class RestAssured {

    /* https://rest-assured.io/:
     Testing and validating REST services in Java is harder than in dynamic languages such as Ruby and Groovy.
     REST Assured brings the simplicity of using these languages into the Java domain. */

    @Test
    public void EqualTo_Assertion() {
        given().baseUri("https://61085830d73c6400170d3911.mockapi.io")
                .when().get("/api/v1/users")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].name", is(equalTo("Abraham Berge")));
    }

    @Test
    public void HasItem_Assertion() {
        given().baseUri("https://61085830d73c6400170d3911.mockapi.io")
                .when().get("/api/v1/users")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].name", is(equalTo("Abraham Berge")))
                .assertThat().body("name" , hasItem("Vicky Lemke"));
    }

    @Test
    public void NotHasItem_Assertion() {
        given().baseUri("https://61085830d73c6400170d3911.mockapi.io")
                .when().get("/api/v1/users")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].name", is(equalTo("Abraham Berge")))
                .assertThat().body("name" , not(hasItem("Ali")));
    }

    @Test
    public void HasItems_Assertion() {
        given().baseUri("https://61085830d73c6400170d3911.mockapi.io")
                .when().get("/api/v1/users")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].name", is(equalTo("Abraham Berge")))
                .assertThat().body("name" , hasItems("Abraham Berge" ,"Vicky Lemke"));
    }

    // While using "Contains" Assertion, you have to take in consideration that order and quantity is essential.
    @Test
    public void Contains_Assertion() {
        given().baseUri("https://61085830d73c6400170d3911.mockapi.io")
                .when().get("/api/v1/users")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].name", is(equalTo("Abraham Berge")))
                .assertThat().body("name" , contains("Abraham Berge" ,"Vicky Lemke"));
    }

    // "ContainsInAnyOrder" Assertion, it only matters with the quantity and ignoring the order.
    @Test
    public void ContainsInAnyOrder_Assertion() {
        given().baseUri("https://61085830d73c6400170d3911.mockapi.io")
                .when().get("/api/v1/users")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].name", is(equalTo("Abraham Berge")))
                .assertThat().body("name" , containsInAnyOrder("Vicky Lemke" ,"Abraham Berge"));
    }


    @Test
    public void Empty_Assertion() {
        given().baseUri("https://61085830d73c6400170d3911.mockapi.io")
                .when().get("/api/v1/users")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].name", is(equalTo("Abraham Berge")))
                .assertThat().body("name" , empty());
    }

    @Test
    public void NotEmpty_Assertion() {
        given().baseUri("https://61085830d73c6400170d3911.mockapi.io")
                .when().get("/api/v1/users")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].name", is(equalTo("Abraham Berge")))
                .assertThat().body("name" , is(not(empty())));
    }

    @Test
    public void EveryItme_Assertion() {
        given().baseUri("https://61085830d73c6400170d3911.mockapi.io")
                .when().get("/api/v1/users")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].name", is(equalTo("Abraham Berge")))
                .assertThat().body("createdAt" , everyItem(startsWith("2021")));
    }

    @Test
    public void HasKey_Assertion() {
        given().baseUri("https://61085830d73c6400170d3911.mockapi.io")
                .when().get("/api/v1/users")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[0].name", is(equalTo("Abraham Berge")))
                .assertThat().body("[0]" , hasKey("id"))
                // "hasEntry" assertion should contain each of the key and value related to each other to pass.
                .assertThat().body("[0]" , hasEntry("id" , "2"));
    }




}
