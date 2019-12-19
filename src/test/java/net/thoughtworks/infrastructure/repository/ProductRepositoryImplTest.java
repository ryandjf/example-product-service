
package net.thoughtworks.infrastructure.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import net.thoughtworks.domain.model.Product;
import net.thoughtworks.infrastructure.dataentity.ProductDataEntity;
import net.thoughtworks.infrastructure.persistence.ProductJpaPersistence;

public class ProductRepositoryImplTest {

    @InjectMocks
    private ProductRepositoryImpl repository;

    @Mock
    private ProductJpaPersistence persistence;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllProducts() {
        List<ProductDataEntity> productDataEntities = new ArrayList<>();

        ProductDataEntity entity = new ProductDataEntity();
        entity.setId(123L);
        entity.setName("China Resources Land");
        productDataEntities.add(entity);

        when(persistence.findAll()).thenReturn(productDataEntities);

        List<Product> result = repository.findAllProducts();

        assertEquals(1, result.size());
        Product product = result.get(0);
        assertEquals(123L, product.getId().longValue());
        assertEquals("China Resources Land", product.getName());
    }
}
