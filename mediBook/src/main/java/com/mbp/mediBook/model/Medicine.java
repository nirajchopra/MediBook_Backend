package com.mbp.mediBook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "medicines")
public class Medicine {
    
    @Id
    private String id;
    
    @Indexed
    private String storeId;
    
    private String name;
    
    private String description;
    
    private String category;
    
    private String manufacturer;
    
    private Double price;
    
    private Double discountPrice;
    
    private Integer stock;
    
    private boolean inStock = true;
    
    private boolean prescriptionRequired = false;
    
    private String dosage;
    
    private String sideEffects;
    
    private String imageUrl;
    
    private LocalDate expiryDate;
    
    private Double rating = 0.0;
    
    private Integer reviewCount = 0;
    
    @CreatedDate
    private LocalDateTime createdAt;
}