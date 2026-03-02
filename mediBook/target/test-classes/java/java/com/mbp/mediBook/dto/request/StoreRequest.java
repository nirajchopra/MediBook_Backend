package com.mbp.mediBook.dto.request;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

	public StoreRequest(String storeIdCode, String storeName, String ownerName, String email, String phone,
			String address, String city, String state, String pincode, String licenseNumber, LocalTime openingTime,
			LocalTime closingTime) {

		this.storeIdCode = storeIdCode;
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
	}

	public String getStoreIdCode() {
		return storeIdCode;
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
}