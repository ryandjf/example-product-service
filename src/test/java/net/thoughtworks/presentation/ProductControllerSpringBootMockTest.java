package net.thoughtworks.presentation;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import net.thoughtworks.application.ProductApplicationService;
import net.thoughtworks.domain.model.Product;
import net.thoughtworks.presentation.assembler.ProductAssembler;
import net.thoughtworks.presentation.dto.ProductResponse;

/**
 * This class demonstrates how to test a controller using Spring Boot Test
 * with a MOCK web environment, which makes it similar to just using @WebMvcTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerSpringBootMockTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductApplicationService service;

    @MockBean
    private ProductAssembler assembler;

    @Test
    public void canRetrieveAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product(123L, "Product Name",
                "The biggest product", "http://z.cn/a.png"));
        given(service.getProducts()).willReturn(products);

        List<ProductResponse> productResponses = new ArrayList<>();
        productResponses.add(new ProductResponse(123L, "Product Name",
                "The biggest product", "http://z.cn/a.png"));
        given(assembler.toProductResponseList(products)).willReturn(productResponses);

        MockHttpServletResponse response = mvc.perform(
                get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
