package com.mbp.mediBook.dto.request;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StoreRequest {
    
    @NotBlank(message = "Store ID code is required")
    private String storeIdCode;
    
    @NotBlank(message = "Store name is required")
    private String storeName;
    
    @NotBlank(message = "Owner name is required")
    private String ownerName;
    
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Phone is required")
    private String phone;
    
    @NotBlank(message = "Address is required")
    private String address;
    
    private String city;
    private String state;
    private String pincode;
    
    @NotBlank(message = "License number is required")
    private String licenseNumber;
    
    private LocalTime openingTime;
    private LocalTime closingTime;
}