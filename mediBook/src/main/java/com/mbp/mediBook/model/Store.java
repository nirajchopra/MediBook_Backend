package com.mbp.mediBook.model;

import com.mbp.mediBook.model.enums.StoreStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
    
    // Constructors
    public Store() {
    }
    
    public Store(String id, String storeIdCode, String userId, String storeName,
                 String ownerName, String email, String phone, String address,
                 String city, String state, String pincode, String licenseNumber,
                 LocalTime openingTime, LocalTime closingTime, StoreStatus status,
                 boolean isActive, LocalDateTime createdAt) {
        this.id = id;
        this.storeIdCode = storeIdCode;
        this.userId = userId;
        this.storeName = storeName;
        this.ownerName = ownerName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.licenseNumber = licenseNumber;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.status = status;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public String getStoreIdCode() {
        return storeIdCode;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getStoreName() {
        return storeName;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getState() {
        return state;
    }
    
    public String getPincode() {
        return pincode;
    }
    
    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    public LocalTime getOpeningTime() {
        return openingTime;
    }
    
    public LocalTime getClosingTime() {
        return closingTime;
    }
    
    public StoreStatus getStatus() {
        return status;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    // Setters
    public void setId(String id) {
        this.id = id;
    }
    
    public void setStoreIdCode(String storeIdCode) {
        this.storeIdCode = storeIdCode;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    
    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }
    
    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }
    
    public void setStatus(StoreStatus status) {
        this.status = status;
    }
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // toString
    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", storeIdCode='" + storeIdCode + '\'' +
                ", userId='" + userId + '\'' +
                ", storeName='" + storeName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                ", status=" + status +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                '}';
    }
}