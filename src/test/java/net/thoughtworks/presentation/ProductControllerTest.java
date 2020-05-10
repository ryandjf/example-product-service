
package net.thoughtworks.presentation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import net.thoughtworks.application.ProductApplicationService;
import net.thoughtworks.domain.model.Product;
import net.thoughtworks.presentation.assembler.ProductAssembler;
import net.thoughtworks.presentation.dto.ProductResponse;

public class ProductControllerTest {

    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductAssembler productAssembler;

    @Mock
    private ProductApplicationService productApplicationService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(123L, "China Resources Land",
                "The biggest product", "http://z.cn/a.png"));
        when(productApplicationService.getProducts()).thenReturn(products);

        ProductResponse expectedResponse = new ProductResponse(123L, "China Resources Land",
                "The biggest product", "http://z.cn/a.png");
        List<ProductResponse> responses = new ArrayList<>();
        responses.add(expectedResponse);

        when(productAssembler.toProductResponseList(products)).thenReturn(responses);

        List<ProductResponse> result = controller.getAllProducts();
        assertEquals(1, result.size());
        ProductResponse productResponse = result.get(0);
        assertEquals(123L, productResponse.getId().longValue());
        assertEquals("China Resources Land", productResponse.getName());
    }
}
