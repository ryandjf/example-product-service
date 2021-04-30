package net.thoughtworks.domain.repository;

import java.util.List;
import java.util.Optional;

import net.thoughtworks.domain.model.Product;

public interface ProductRepository {
    List<Product> findAllProducts();

    Optional<Product> findProductById(Long productId);
}
