package com.mbp.mediBook.service;

import java.util.List;

import com.mbp.mediBook.dto.request.OrderRequest;
import com.mbp.mediBook.model.Order;
import com.mbp.mediBook.model.enums.OrderStatus;

public interface OrderService {
    Order createOrder(OrderRequest request);
    List<Order> getUserOrders(String userId);
    List<Order> getStoreOrders(String storeId);
    List<Order> getAllOrders();
    Order getOrderById(String id);
    Order updateOrderStatus(String id, OrderStatus status);
    void cancelOrder(String id);
}