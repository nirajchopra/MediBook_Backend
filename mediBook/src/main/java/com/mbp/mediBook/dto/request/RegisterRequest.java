package com.mbp.mediBook.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class RegisterRequest {
    
    @NotBlank(message = "Full name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String fullName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone must be 10 digits")
    private String phoneNumber;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    public RegisterRequest(String fullName, String email, String phoneNumber, String password) {
    	this.fullName = fullName;
    	this.email = email;
    	this.password = password;
    	this.phoneNumber = phoneNumber;
    }
    
    public String getFullName() {
    	return fullName;
    }
    public String getEmail() {
    	return email;
    }
    public String getPhoneNumber() {
    	return phoneNumber;
    }
    public String getPassword() {
    	return password;
    }
    
    public void setFullName(String fullName) {
    	this.fullName = fullName;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
}