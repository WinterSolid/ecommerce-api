package com.wintersolidstudios.ecommerce_api.service;

import com.wintersolidstudios.ecommerce_api.dto.CreateOrderItemRequest;
import com.wintersolidstudios.ecommerce_api.dto.CreateOrderRequest;
import com.wintersolidstudios.ecommerce_api.dto.OrderResponse;
import com.wintersolidstudios.ecommerce_api.entity.*;
import com.wintersolidstudios.ecommerce_api.exception.InsufficientStockException;
import com.wintersolidstudios.ecommerce_api.repository.OrderRepository;
import com.wintersolidstudios.ecommerce_api.repository.ProductRepository;
import com.wintersolidstudios.ecommerce_api.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(
            UserRepository userRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository
    ) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {

        // Find user placing order
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<OrderItem> orderItems = new ArrayList<>();

        double totalAmount = 0.0;

        // Create order
        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();


        // Process each product in order
        for (CreateOrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Check inventory
            if (product.getStockQuantity() < itemRequest.getQuantity()) {

                throw new InsufficientStockException( "Insufficient stock for product: " + product.getName());
            }

            // Deduct inventory
            product.setStockQuantity( product.getStockQuantity() - itemRequest.getQuantity());

            productRepository.save(product);

            // Create order item
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();


            totalAmount += product.getPrice() * itemRequest.getQuantity();

            orderItems.add(orderItem);
        }

        // Attach items to order
        order.setItems(orderItems);

        order.setTotalAmount(totalAmount);

        // Save order
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.builder()
                .orderId(savedOrder.getId())
                .totalAmount(savedOrder.getTotalAmount())
                .status(savedOrder.getStatus().name())
                .build();
    }
}