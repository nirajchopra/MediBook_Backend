package com.mbp.mediBook.model;

import com.mbp.mediBook.*;
import com.mbp.mediBook.model.enums.StoreStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "stores")
public class Store {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String storeIdCode;
    
    @Indexed
    private String userId;
    
    private String storeName;
    
    private String ownerName;
    
    private String email;
    
    private String phone;
    
    private String address;
    
    private String city;
    
    private String state;
    
    private String pincode;
    
    private String licenseNumber;
    
    private LocalTime openingTime;
    
    private LocalTime closingTime;
    
    private StoreStatus status = StoreStatus.PENDING;
    
    private boolean isActive = true;
    
    @CreatedDate
    private LocalDateTime createdAt;
}