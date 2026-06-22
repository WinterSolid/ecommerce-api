package com.wintersolidstudios.ecommerce_api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequest {

    private Long userId;

    private List<CreateOrderItemRequest> items;
}