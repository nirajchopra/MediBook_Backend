package com.mbp.mediBook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicineRequest {
    
    @NotBlank(message = "Medicine name is required")
    private String name;
    
    private String description;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;
    
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;
    
    private Double discountPrice;
    
    @NotNull(message = "Stock is required")
    private Integer stock;
    
    private boolean prescriptionRequired;
    
    private String dosage;
    
    private String imageUrl;
    
    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;
}