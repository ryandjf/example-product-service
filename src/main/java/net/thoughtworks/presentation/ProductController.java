package net.thoughtworks.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import net.thoughtworks.application.ProductApplicationService;
import net.thoughtworks.domain.model.Product;
import net.thoughtworks.exceptions.NonExistingProductException;
import net.thoughtworks.presentation.assembler.ProductAssembler;
import net.thoughtworks.presentation.dto.ProductResponse;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ProductAssembler productAssembler;
    private ProductApplicationService productApplicationService;

    @Autowired
    public ProductController(ProductAssembler productAssembler, ProductApplicationService productApplicationService) {
        this.productAssembler = productAssembler;
        this.productApplicationService = productApplicationService;
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        final List<Product> products = productApplicationService.getProducts();
        return productAssembler.toProductResponseList(products);
    }

    @GetMapping(value = "/{productId}", headers = "Accept=application/json")
    public ProductResponse getProductById(@PathVariable("productId") final Long productId) {
        final Optional<Product> product = productApplicationService.getProductById(productId);
        if (product.isPresent()) {
            return productAssembler.toProductResponse(product.get());
        } else {
            logger.info("Cannot find product with id '{0}'.", productId);
            throw new NonExistingProductException(String.format("Product with id '%s' does not exist.", productId));
        }
    }
}
