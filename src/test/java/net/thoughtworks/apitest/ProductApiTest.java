package net.thoughtworks.apitest;

import org.junit.Test;

public class ProductApiTest extends BaseApiTest {

    @Test
    public void should_get_all_products() {
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }

}