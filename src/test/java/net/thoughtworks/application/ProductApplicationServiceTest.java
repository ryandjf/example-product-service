package net.thoughtworks.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

import net.thoughtworks.domain.model.Product;
import net.thoughtworks.domain.repository.ProductRepository;

public class ProductApplicationServiceTest {
    @InjectMocks
    private ProductApplicationService productApplicationService;

    @Mock
    private ProductRepository repository;

    private AutoCloseable closeable;

    @Before
    public void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void closeMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void shouldGetAllProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(123L, "Product Name",
                "The biggest product", "http://z.cn/a.png"));

        when(repository.findAllProducts()).thenReturn(products);

        List<Product> result = productApplicationService.getProducts();

        assertEquals(1, result.size());
        Product product = result.get(0);
        assertEquals(123L, product.getId().longValue());
        assertEquals("Product Name", product.getName());
    }

    @Test
    public void shouldGetProductById() {
        Product product = new Product(123L, "Product Name",
                "The biggest product", "http://z.cn/a.png");
        when(repository.findProductById(123L)).thenReturn(Optional.of(product));

        Optional<Product> result = productApplicationService.getProductById(123L);

        assertTrue(result.isPresent());
        Product actualProduct = result.get();
        assertEquals(123L, actualProduct.getId().longValue());
        assertEquals("Product Name", actualProduct.getName());
    }
}
