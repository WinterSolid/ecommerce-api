package com.wintersolidstudios.ecommerce_api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Positive
    private Double price;

    @NotBlank
    private String category;

    @NotNull
    @Min(0)
    private Integer stockQuantity;
}