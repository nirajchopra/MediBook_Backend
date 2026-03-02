package com.mbp.mediBook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

	public MedicineRequest(String name, String description, String category, String manufacturer, Double price,
			Double discountPrice, Integer stock, String dosage, String imageUrl, LocalDate expiryDate) {

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
	public String getDosage() {
		return dosage;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public boolean isPrescriptionRequired() {
		return false;
	}
}