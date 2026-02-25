package com.mbp.mediBook.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mbp.mediBook.model.Order;
import com.mbp.mediBook.model.enums.OrderStatus;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    
    List<Order> findByUserId(String userId);
    
    List<Order> findByStoreId(String storeId);
    
    List<Order> findByStatus(OrderStatus status);
    
    List<Order> findByStoreIdAndStatus(String storeId, OrderStatus status);
    
    Optional<Order> findByOrderNumber(String orderNumber);
    
    long countByStoreIdAndStatus(String storeId, OrderStatus status);
    
    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}