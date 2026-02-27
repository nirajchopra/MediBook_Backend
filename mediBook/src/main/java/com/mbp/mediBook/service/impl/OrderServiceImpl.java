package com.mbp.mediBook.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbp.mediBook.dto.request.OrderRequest;
import com.mbp.mediBook.exception.BadRequestException;
import com.mbp.mediBook.exception.ResourceNotFoundException;
import com.mbp.mediBook.model.Order;
import com.mbp.mediBook.model.Store;
import com.mbp.mediBook.model.enums.OrderStatus;
import com.mbp.mediBook.repository.MedicineRepository;
import com.mbp.mediBook.repository.OrderRepository;
import com.mbp.mediBook.repository.StoreRepository;
import com.mbp.mediBook.service.AuthService;
import com.mbp.mediBook.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.var;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private final MedicineRepository medicineRepository;
    private final AuthService authService;
    
    @Override
    @Transactional
    public Order createOrder(OrderRequest request) {
        var currentUser = authService.getCurrentUser();
        
        // Get store details
        Store store = storeRepository.findById(request.getStoreId())
            .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        
        Order order = new Order();
        order.setOrderNumber(generateOrderNumber());
        order.setUserId(currentUser.getId());
        order.setStoreId(store.getId());
        order.setStoreName(store.getStoreName());
        order.setStoreAddress(store.getAddress());
        order.setCustomerName(currentUser.getFullName());
        order.setCustomerPhone(currentUser.getPhoneNumber());
        order.setItems(request.getItems());
        order.setSubtotal(request.getSubtotal());
        order.setDiscount(request.getDiscount());
        order.setTotal(request.getTotal());
        order.setStatus(OrderStatus.PENDING);
        order.setPickupDate(request.getPickupDate());
        order.setPickupTime(request.getPickupTime());
        
        return orderRepository.save(order);
    }
    
    @Override
    public List<Order> getUserOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }
    
    @Override
    public List<Order> getStoreOrders(String storeId) {
        return orderRepository.findByStoreId(storeId);
    }
    
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }
    
    @Override
    @Transactional
    public Order updateOrderStatus(String id, OrderStatus status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }
    
    @Override
    @Transactional
    public void cancelOrder(String id) {
        Order order = getOrderById(id);
        
        if (order.getStatus() == OrderStatus.COMPLETED) {
            throw new BadRequestException("Cannot cancel completed order");
        }
        
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
    
    private String generateOrderNumber() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String random = String.format("%03d", new Random().nextInt(1000));
        return "ORD" + timestamp.substring(timestamp.length() - 6) + random;
    }
}