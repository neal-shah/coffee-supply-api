package com.coffeesupply

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class CoffeeStockResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/coffee")
          .then()
             .statusCode(200)
             .body(`is`("hello"))
    }

}