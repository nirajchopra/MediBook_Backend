package com.mbp.mediBook.model;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mbp.mediBook.model.enums.OrderStatus;

@Document(collection = "orders")
public class Order {

	@Id
	private String id;

	@Indexed
	private String orderNumber;

	@Indexed
	private String userId;

	@Indexed
	private String storeId;

	private String storeName;

	private String storeAddress;

	private String customerName;

	private String customerPhone;

	private List<OrderItem> items;

	private Double subtotal;

	private Double discount;

	private Double total;

	private OrderStatus status = OrderStatus.PENDING;

	private LocalDate pickupDate;

	private String pickupTime;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	// Constructors
	public Order() {
	}

	public Order(String id, String orderNumber, String userId, String storeId, String storeName, String storeAddress,
			String customerName, String customerPhone, List<OrderItem> items, Double subtotal, Double discount,
			Double total, OrderStatus status, LocalDate pickupDate, String pickupTime, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.orderNumber = orderNumber;
		this.userId = userId;
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.items = items;
		this.subtotal = subtotal;
		this.discount = discount;
		this.total = total;
		this.status = status;
		this.pickupDate = pickupDate;
		this.pickupTime = pickupTime;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	// Getters
	public String getId() {
		return id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public String getUserId() {
		return userId;
	}

	public String getStoreId() {
		return storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public List<OrderItem> getItems() {
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

	public OrderStatus getStatus() {
		return status;
	}

	public LocalDate getPickupDate() {
		return pickupDate;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	// Setters
	public void setId(String id) {
		this.id = id;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public void setPickupDate(LocalDate pickupDate) {
		this.pickupDate = pickupDate;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	// toString
	@Override
	public String toString() {
		return "Order{" + "id='" + id + '\'' + ", orderNumber='" + orderNumber + '\'' + ", userId='" + userId + '\''
				+ ", storeId='" + storeId + '\'' + ", storeName='" + storeName + '\'' + ", customerName='"
				+ customerName + '\'' + ", customerPhone='" + customerPhone + '\'' + ", items=" + items + ", subtotal="
				+ subtotal + ", discount=" + discount + ", total=" + total + ", status=" + status + ", pickupDate="
				+ pickupDate + ", pickupTime='" + pickupTime + '\'' + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + '}';
	}

	// Inner class OrderItem
	public static class OrderItem {
		private String medicineId;
		private String medicineName;
		private Integer quantity;
		private Double price;

		// Constructors
		public OrderItem() {
		}

		public OrderItem(String medicineId, String medicineName, Integer quantity, Double price) {
			this.medicineId = medicineId;
			this.medicineName = medicineName;
			this.quantity = quantity;
			this.price = price;
		}

		// Getters
		public String getMedicineId() {
			return medicineId;
		}

		public String getMedicineName() {
			return medicineName;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public Double getPrice() {
			return price;
		}

		// Setters
		public void setMedicineId(String medicineId) {
			this.medicineId = medicineId;
		}

		public void setMedicineName(String medicineName) {
			this.medicineName = medicineName;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		@Override
		public String toString() {
			return "OrderItem{" + "medicineId='" + medicineId + '\'' + ", medicineName='" + medicineName + '\''
					+ ", quantity=" + quantity + ", price=" + price + '}';
		}
	}
}