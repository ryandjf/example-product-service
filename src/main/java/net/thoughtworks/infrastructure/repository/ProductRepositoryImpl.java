package net.thoughtworks.infrastructure.repository;

import net.thoughtworks.domain.repository.ProductRepository;
import net.thoughtworks.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    public List<Product> findAllProducts() {
        List<Product> result = new ArrayList<>();
        result.add(new Product(123L, "China Resources Land"));
        return result;
    }
}
