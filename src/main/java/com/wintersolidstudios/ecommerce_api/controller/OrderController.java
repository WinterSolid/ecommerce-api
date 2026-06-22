package com.wintersolidstudios.ecommerce_api.controller;

import com.wintersolidstudios.ecommerce_api.dto.CreateOrderRequest;
import com.wintersolidstudios.ecommerce_api.dto.OrderResponse;
import com.wintersolidstudios.ecommerce_api.service.OrderService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponse createOrder(
            @Valid @RequestBody CreateOrderRequest request
    ) {
        return service.createOrder(request);
    }
}