package com.coffeesupply

import io.quarkus.test.common.http.TestHTTPResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation

@TestHTTPResource
private val URL = "/coffee"

@QuarkusTest
@TestMethodOrder(OrderAnnotation::class)
class CoffeeResourceTest {

    @Test
    @Order(1)
    fun testGetAllCoffee() {
        given()
                .`when`()
                .get(URL)
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", `is`(5))
                .body("[0].id", `is`(1))
                .body("[3].productName", `is`("El Limon"))
                .extract()
    }

    @Test
    @Order(2)
    fun testAddNewCoffeeToInventory() {
        given()
                .`when`()
                .contentType(ContentType.JSON)
                .body("""{
                        "sku": 10007,
                        "productName": "Encanto",
                        "description": "Papaya, milk chocolate, muscavado",
                        "originCountry": "Nicaragua",
                        "price": 9.00
                    }""")
                .post(URL)
                .then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", `is`(6))
                .body("originCountry", `is`("Nicaragua"))
    }

    @Test
    @Order(3)
    fun testDeleteCoffeeFromInventory() {
        given()
                .`when`()
                .delete("$URL/{id}", 4)
                .then()
                .log().all()
                .statusCode(200)
                .body(`is`("Item id 4 deleted."))
    }

    @Test
    @Order(4)
    fun testUpdateCoffeePrice() {
        given()
                .`when`()
                .contentType(ContentType.JSON)
                .body("""{
                        "sku": 10003,
                        "productName": "Mama Mina",
                        "description": "Toffee apple, orange rid, sweet tobacco",
                        "originCountry": "Nicaragua",
                        "price": 9.75
                    }""")
                .put("$URL/{id}", 3)
                .then()
                .log().all()
                .statusCode(200)
                .body("price", `is`(9.75.toFloat()))
    }

    @Test
    @Order(5)
    fun testSearchByOriginCountry() {
        given()
                .`when`()
                .get("$URL/search?originCountry=Kenya")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", `is`(1))
                .body("[0].productName", `is`("Kiriga AB"))
    }
}