package net.thoughtworks.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {

    private Long id;

    private String name;

    private String description;

    private String picture;
}
