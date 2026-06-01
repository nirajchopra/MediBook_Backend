package com.mbp.mediBook.service;

import java.util.List;

import com.mbp.mediBook.dto.request.OrderRequest;
import com.mbp.mediBook.model.Order;
import com.mbp.mediBook.model.enums.OrderStatus;

public interface OrderService {
    Order createOrder(OrderRequest request);
    List<Order> getUserOrders(Long userId);
    List<Order> getStoreOrders(Long storeId);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order updateOrderStatus(Long id, OrderStatus status);
    void cancelOrder(Long id);
}