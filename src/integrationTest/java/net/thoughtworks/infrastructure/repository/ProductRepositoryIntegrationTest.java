package net.thoughtworks.infrastructure.repository;

import net.thoughtworks.domain.model.Product;
import net.thoughtworks.domain.repository.ProductRepository;
import net.thoughtworks.infrastructure.dataentity.ProductDataEntity;
import net.thoughtworks.infrastructure.persistence.ProductJpaPersistence;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ProductRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductJpaPersistence persistence;

    private ProductRepository repository;

    @Before
    public void setUp() {
        repository = new ProductRepositoryImpl(persistence);
    }

    @Test
    public void findAllProductsTest() {
        ProductDataEntity product = new ProductDataEntity();
        product.setName("Test Product");
        entityManager.persist(product);
        entityManager.flush();

        List<Product> products = repository.findAllProducts();

        assertEquals(1, products.size());
    }
}
