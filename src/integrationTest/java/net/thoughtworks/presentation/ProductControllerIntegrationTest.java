
package net.thoughtworks.presentation;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProductControllerIntegrationTest {
    @Autowired
    protected WebApplicationContext context;

    protected MockMvcRequestSpecification given() {
        return RestAssuredMockMvc.given().webAppContextSetup(context);
    }

    @Test
    public void shouldGetAllProducts() {
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }
}
