package com.mbp.mediBook.model;

import com.mbp.mediBook.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    
    @Id
    private String id;
    
    @Indexed
    private String orderNumber;
    
    @Indexed
    private String userId;
    
    @Indexed
    private String storeId;
    
    private String storeName;
    
    private String storeAddress;
    
    private String customerName;
    
    private String customerPhone;
    
    private List<OrderItem> items;
    
    private Double subtotal;
    
    private Double discount;
    
    private Double total;
    
    private OrderStatus status = OrderStatus.PENDING;
    
    private LocalDate pickupDate;
    
    private String pickupTime;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem {
        private String medicineId;
        private String medicineName;
        private Integer quantity;
        private Double price;
    }
}