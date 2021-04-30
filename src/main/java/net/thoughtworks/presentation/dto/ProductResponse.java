package net.thoughtworks.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@Data
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private String picture;
}
