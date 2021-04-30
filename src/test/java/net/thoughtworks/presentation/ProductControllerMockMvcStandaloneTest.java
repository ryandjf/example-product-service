package net.thoughtworks.presentation;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import net.thoughtworks.application.ProductApplicationService;
import net.thoughtworks.domain.model.Product;
import net.thoughtworks.presentation.assembler.ProductAssembler;

/**
 * This class demonstrates how to test a controller using MockMVC with Standalone setup.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerMockMvcStandaloneTest {
    private MockMvc mvc;
    private ProductController controller;

    @Mock
    private ProductApplicationService service;

    @Before
    public void setup() {
        controller = new ProductController(new ProductAssembler(), service);
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ProductExceptionHandler())
                .build();
    }

    @Test
    public void canRetrieveAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product(123L, "Product Name",
                "The biggest product", "http://z.cn/a.png"));
        given(service.getProducts()).willReturn(products);

        MockHttpServletResponse response = mvc.perform(get("/products")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        System.out.println(response.getContentAsString());
    }
}
