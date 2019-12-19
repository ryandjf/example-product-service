
package net.thoughtworks.domain.repository;

import java.util.List;

import net.thoughtworks.domain.model.Product;

public interface ProductRepository {
    List<Product> findAllProducts();
}
