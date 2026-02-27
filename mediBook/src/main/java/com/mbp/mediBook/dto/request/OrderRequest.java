package com.mbp.mediBook.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.mbp.mediBook.model.Order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {
    
    @NotBlank(message = "Store ID is required")
    private String storeId;
    
    @NotEmpty(message = "Order items cannot be empty")
    private List<Order.OrderItem> items;
    
    @NotNull(message = "Subtotal is required")
    private Double subtotal;
    
    private Double discount = 0.0;
    
    @NotNull(message = "Total is required")
    private Double total;
    
    @NotNull(message = "Pickup date is required")
    private LocalDate pickupDate;
    
    @NotBlank(message = "Pickup time is required")
    private String pickupTime;
}