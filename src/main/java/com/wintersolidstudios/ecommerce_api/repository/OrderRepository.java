package com.wintersolidstudios.ecommerce_api.repository;

import com.wintersolidstudios.ecommerce_api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository
        extends JpaRepository<Order, Long> {
}