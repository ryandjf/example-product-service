
package net.thoughtworks.application;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import net.thoughtworks.domain.model.Product;
import net.thoughtworks.domain.repository.ProductRepository;

@Component
public class ProductApplicationService {

    private final ProductRepository repository;

    public ProductApplicationService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getProducts() {
        return repository.findAllProducts();
    }

    public Optional<Product> getProductById(Long productId){
        return repository.findProductById(productId);
    }
}
