package net.thoughtworks.presentation.assembler;

import net.thoughtworks.domain.model.Product;
import net.thoughtworks.presentation.dto.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductAssembler {
    protected static final ModelMapper mapper = new ModelMapper();

    public List<ProductResponse> toProductResponseList(List<Product> products) {

        return products.stream()
                .map(c -> mapper.map(c, ProductResponse.class))
                .collect(Collectors.toList());
    }
}