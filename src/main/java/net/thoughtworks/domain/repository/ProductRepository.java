package net.thoughtworks.domain.repository;

import net.thoughtworks.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAllProducts();
}
