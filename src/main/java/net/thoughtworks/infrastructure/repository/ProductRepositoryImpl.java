
package net.thoughtworks.infrastructure.repository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import net.thoughtworks.domain.model.Product;
import net.thoughtworks.domain.repository.ProductRepository;
import net.thoughtworks.infrastructure.dataentity.ProductDataEntity;
import net.thoughtworks.infrastructure.persistence.ProductJpaPersistence;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    protected static final ModelMapper mapper = new ModelMapper();

    private ProductJpaPersistence persistence;

    @Autowired
    public ProductRepositoryImpl(ProductJpaPersistence persistence) {
        this.persistence = persistence;
    }

    public List<Product> findAllProducts() {
        List<Product> result = new ArrayList<>();
        Iterable<ProductDataEntity> iterable = persistence.findAll();
        iterable.forEach(productDataEntity -> result.add(mapper.map(productDataEntity, Product.class)));
        return result;
    }
}
