package com.aryan.api_automation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApiChainTest {

    int userId, productId, orderId, paymentId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(priority = 1)
    public void createUser() {
        userId = given().contentType(ContentType.JSON)
                .body(Map.of("name", "Aryan", "email", "aryan@test.com"))
                .when().post("/users")
                .then().statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Aryan"))
                .extract().path("id");
    }

    @Test(priority = 2)
    public void createProduct() {
        productId = given().contentType(ContentType.JSON)
                .body(Map.of("name", "Keyboard", "price", 1500, "stock", 10))
                .when().post("/products")
                .then().statusCode(201)
                .body("name", equalTo("Keyboard"))
                .extract().path("id");
    }

    @Test(priority = 3, dependsOnMethods = {"createUser", "createProduct"})
    public void createOrder() {
        orderId = given().contentType(ContentType.JSON)
                .body(Map.of("userId", userId, "productId", productId, "quantity", 2))
                .when().post("/orders")
                .then().statusCode(201)
                .body("status", equalTo("CREATED"))
                .extract().path("id");
    }

    @Test(priority = 4, dependsOnMethods = "createOrder")
    public void createPayment() {
        paymentId = given().contentType(ContentType.JSON)
                .body(Map.of("orderId", orderId, "amount", 3000))
                .when().post("/payments")
                .then().statusCode(201)
                .body("status", equalTo("SUCCESS"))
                .extract().path("id");
    }

    @Test(priority = 5, dependsOnMethods = "createPayment")
    public void orderIsPaid() {
        given()
                .when().get("/orders/" + orderId)
                .then().statusCode(200)
                .body("status", equalTo("PAID"));
    }

    @Test(priority = 6, dependsOnMethods = "createProduct")
    public void updateProduct() {
        given().contentType(ContentType.JSON)
                .body(Map.of("name", "Keyboard Pro", "price", 2000, "stock", 8))
                .when().put("/products/" + productId)
                .then().statusCode(200)
                .body("name", equalTo("Keyboard Pro"));
    }

    @Test(priority = 7)
    public void missingUserReturns404() {
        given()
                .when().get("/users/99999")
                .then().statusCode(404);
    }

    @Test(priority = 8, dependsOnMethods = "createPayment")
    public void deletePayment() {
        given()
                .when().delete("/payments/" + paymentId)
                .then().statusCode(204);
    }
}