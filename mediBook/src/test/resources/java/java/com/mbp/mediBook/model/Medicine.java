package com.mbp.mediBook.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
    
    // Constructors
    public Medicine() {
    }
    
    public Medicine(String id, String storeId, String name, String description,
                    String category, String manufacturer, Double price,
                    Double discountPrice, Integer stock, boolean inStock,
                    boolean prescriptionRequired, String dosage, String sideEffects,
                    String imageUrl, LocalDate expiryDate, Double rating,
                    Integer reviewCount, LocalDateTime createdAt) {
        this.id = id;
        this.storeId = storeId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.manufacturer = manufacturer;
        this.price = price;
        this.discountPrice = discountPrice;
        this.stock = stock;
        this.inStock = inStock;
        this.prescriptionRequired = prescriptionRequired;
        this.dosage = dosage;
        this.sideEffects = sideEffects;
        this.imageUrl = imageUrl;
        this.expiryDate = expiryDate;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.createdAt = createdAt;
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public String getStoreId() {
        return storeId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public Double getDiscountPrice() {
        return discountPrice;
    }
    
    public Integer getStock() {
        return stock;
    }
    
    public boolean isInStock() {
        return inStock;
    }
    
    public boolean isPrescriptionRequired() {
        return prescriptionRequired;
    }
    
    public String getDosage() {
        return dosage;
    }
    
    public String getSideEffects() {
        return sideEffects;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    
    public Double getRating() {
        return rating;
    }
    
    public Integer getReviewCount() {
        return reviewCount;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    // Setters
    public void setId(String id) {
        this.id = id;
    }
    
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
    
    public void setPrescriptionRequired(boolean prescriptionRequired) {
        this.prescriptionRequired = prescriptionRequired;
    }
    
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
    
    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // toString
    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", storeId='" + storeId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", stock=" + stock +
                ", inStock=" + inStock +
                ", prescriptionRequired=" + prescriptionRequired +
                ", dosage='" + dosage + '\'' +
                ", sideEffects='" + sideEffects + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", expiryDate=" + expiryDate +
                ", rating=" + rating +
                ", reviewCount=" + reviewCount +
                ", createdAt=" + createdAt +
                '}';
    }
    
    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return id != null && id.equals(medicine.id);
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}