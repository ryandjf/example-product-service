package net.thoughtworks.infrastructure.repository;

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
import net.thoughtworks.infrastructure.dataentity.ProductDataEntity;
import net.thoughtworks.infrastructure.persistence.ProductJpaPersistence;

public class ProductRepositoryImplTest {

    @InjectMocks
    private ProductRepositoryImpl repository;

    @Mock
    private ProductJpaPersistence persistence;

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
    public void findAllProducts() {
        ProductDataEntity entity = new ProductDataEntity();
        entity.setId(123L);
        entity.setName("Product Name");
        entity.setDescription("Product description");
        entity.setPicture("New picture");

        List<ProductDataEntity> productDataEntities = new ArrayList<>();
        productDataEntities.add(entity);

        when(persistence.findAll()).thenReturn(productDataEntities);

        List<Product> result = repository.findAllProducts();

        assertEquals(1, result.size());
        Product product = result.get(0);
        assertEquals(123L, product.getId().longValue());
        assertEquals("Product Name", product.getName());
        assertEquals("Product description", product.getDescription());
        assertEquals("New picture", product.getPicture());
    }

    @Test
    public void findProductById() {
        ProductDataEntity entity = new ProductDataEntity();
        entity.setId(123L);
        entity.setName("Product Name");
        entity.setDescription("Product description");
        entity.setPicture("New picture");

        when(persistence.findById(123L)).thenReturn(Optional.of(entity));

        Optional<Product> result = repository.findProductById(123L);
        assertTrue(result.isPresent());
        Product product = result.get();
        assertEquals(123L, product.getId().longValue());
        assertEquals("Product Name", product.getName());
        assertEquals("Product description", product.getDescription());
        assertEquals("New picture", product.getPicture());
    }
}
