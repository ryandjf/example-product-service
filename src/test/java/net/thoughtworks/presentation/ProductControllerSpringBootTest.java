package net.thoughtworks.presentation;

import net.thoughtworks.application.ProductApplicationService;
import net.thoughtworks.domain.model.Product;
import net.thoughtworks.presentation.assembler.ProductAssembler;
import net.thoughtworks.presentation.dto.ProductResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * This class demonstrates how to test a controller using Spring Boot Test
 * (what makes it much closer to a Integration Test).
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerSpringBootTest {
    @MockBean
    private ProductApplicationService service;

    @MockBean
    private ProductAssembler assembler;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void canRetrieveAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(123L, "Product Name"));
        given(service.getProducts()).willReturn(products);

        List<ProductResponse> productResponses = new ArrayList<>();
        productResponses.add(new ProductResponse(123L, "Product Name"));
        given(assembler.toProductResponseList(products)).willReturn(productResponses);


        ResponseEntity<List<ProductResponse>> response = restTemplate.exchange("/products", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<ProductResponse>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}