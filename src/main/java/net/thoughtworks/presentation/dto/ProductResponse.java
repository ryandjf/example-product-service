package net.thoughtworks.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@Data
public class ProductResponse {

    private Long id;

    private String name;
}
