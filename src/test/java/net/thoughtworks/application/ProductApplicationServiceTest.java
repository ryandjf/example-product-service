package net.thoughtworks.application;

import net.thoughtworks.domain.model.Product;
import net.thoughtworks.domain.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ProductApplicationServiceTest {
    @InjectMocks
    private ProductApplicationService productApplicationService;

    @Mock
    private ProductRepository repository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetAllProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(123L, "China Resources Land"));

        when(repository.findAllProducts()).thenReturn(products);

        List<Product> result = productApplicationService.getProducts();

        assertEquals(1, result.size());
        Product product = result.get(0);
        assertEquals(123L, product.getId().longValue());
        assertEquals("China Resources Land", product.getName());
    }
}
