package com.mbp.mediBook.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbp.mediBook.model.Order;
import com.mbp.mediBook.model.enums.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByUserId(Long userId);
    
    List<Order> findByStoreId(Long storeId);
    
    List<Order> findByStatus(OrderStatus status);
    
    List<Order> findByStoreIdAndStatus(Long storeId, OrderStatus status);
    
    Optional<Order> findByOrderNumber(String orderNumber);
    
    long countByStoreIdAndStatus(Long storeId, OrderStatus status);
    
    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}