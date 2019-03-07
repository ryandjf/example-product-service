package net.thoughtworks.infrastructure.repository;

import net.thoughtworks.domain.model.Product;
import net.thoughtworks.domain.repository.ProductRepository;
import net.thoughtworks.infrastructure.persistence.ProductJpaPersistence;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    protected static final ModelMapper mapper = new ModelMapper();

    @Autowired
    private ProductJpaPersistence persistence;

    public List<Product> findAllProducts() {
        List<Product> result = new ArrayList<>();
        result.add(new Product(123L, "China Resources Land"));
        return result;
    }
}
