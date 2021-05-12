package net.thoughtworks.presentation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.thoughtworks.application.ProductApplicationService;
import net.thoughtworks.domain.model.Product;
import net.thoughtworks.exceptions.NonExistingProductException;
import net.thoughtworks.presentation.assembler.ProductAssembler;
import net.thoughtworks.presentation.dto.ProductResponse;

public class ProductControllerTest {

    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductAssembler productAssembler;

    @Mock
    private ProductApplicationService productApplicationService;

    private AutoCloseable closeable;

    @Before
    public void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void closeMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void getAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(123L, "Product Name",
                "The biggest product", "http://z.cn/a.png"));
        when(productApplicationService.getProducts()).thenReturn(products);

        ProductResponse expectedResponse = new ProductResponse(123L, "Product Name",
                "The biggest product", "http://z.cn/a.png");
        List<ProductResponse> responses = new ArrayList<>();
        responses.add(expectedResponse);

        when(productAssembler.toProductResponseList(products)).thenReturn(responses);

        List<ProductResponse> result = controller.getAllProducts();
        assertEquals(1, result.size());
        ProductResponse productResponse = result.get(0);
        assertEquals(123L, productResponse.getId().longValue());
        assertEquals("Product Name", productResponse.getName());
    }

    @Test
    public void getProductById() {
        long productId = 123L;
        Product product = new Product(productId, "Product Name",
                "The biggest product", "http://z.cn/a.png");
        when(productApplicationService.getProductById(productId)).thenReturn(Optional.of(product));

        ProductResponse expectedResponse = new ProductResponse(123L, "Product Name",
                "The biggest product", "http://z.cn/a.png");
        when(productAssembler.toProductResponse(product)).thenReturn(expectedResponse);

        ProductResponse result = controller.getProductById(productId);
        assertEquals(productId, result.getId().longValue());
        assertEquals("Product Name", result.getName());
    }

    @Test
    public void getProductByIdNotFound() {
        when(productApplicationService.getProductById(456L)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NonExistingProductException.class,
                () -> controller.getProductById(456L));
        assertEquals("Product with id '456' does not exist.", exception.getMessage());
    }
}
