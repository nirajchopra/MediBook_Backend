package com.mbp.mediBook.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.mbp.mediBook.model.Order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

	public OrderRequest(String storeId, List<Order.OrderItem> items, Double subtotal, Double discount, Double total,
			LocalDate pickupDate, String pickupTime) {
		this.storeId = storeId;
		this.items = items;
		this.subtotal = subtotal;
		this.discount = discount;
		this.total = total;
		this.pickupDate = pickupDate;
		this.pickupTime = pickupTime;
	}
	
	public String getStoreId() {
		return storeId;
	}
	public List<Order.OrderItem> getItems(){
		return items;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public Double getDiscount() {
		return discount;
	}
	public Double getTotal() {
		return total;
	}
	public LocalDate getPickupDate() {
		return pickupDate;
	}
	public String getPickupTime() {
		return pickupTime;
	}
}