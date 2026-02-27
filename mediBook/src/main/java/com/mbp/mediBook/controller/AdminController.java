package com.mbp.mediBook.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.mongodb.core.aggregation.VariableOperators.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbp.mediBook.dto.response.MessageResponse;
import com.mbp.mediBook.model.Order;
import com.mbp.mediBook.model.Store;
import com.mbp.mediBook.model.User;
import com.mbp.mediBook.model.enums.StoreStatus;
import com.mbp.mediBook.repository.OrderRepository;
import com.mbp.mediBook.repository.StoreRepository;
import com.mbp.mediBook.service.OrderService;
import com.mbp.mediBook.service.StoreService;
import com.mbp.mediBook.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class AdminController {
    
    private final UserService userService;
    private final StoreService storeService;
    private final OrderService orderService;
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    
    @PatchMapping("/users/{id}/toggle-status")
    public ResponseEntity<User> toggleUserStatus(@PathVariable String id) {
        return ResponseEntity.ok(userService.toggleUserStatus(id));
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageResponse(true, "User deleted successfully"));
    }
    
    @GetMapping("/stores")
    public ResponseEntity<List<Store>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }
    
    @GetMapping("/stores/pending")
    public ResponseEntity<List<Store>> getPendingStores() {
        return ResponseEntity.ok(storeService.getStoresByStatus(StoreStatus.PENDING));
    }
    
    @PostMapping("/stores/{id}/approve")
    public ResponseEntity<Store> approveStore(@PathVariable String id) {
        return ResponseEntity.ok(storeService.approveStore(id));
    }
    
    @PostMapping("/stores/{id}/reject")
    public ResponseEntity<Store> rejectStore(@PathVariable String id) {
        return ResponseEntity.ok(storeService.rejectStore(id));
    }
    
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getPlatformStats() {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalUsers", userService.getAllUsers().size());
        stats.put("totalStores", storeRepository.countByStatus(StoreStatus.APPROVED));
        stats.put("pendingApprovals", storeRepository.countByStatus(StoreStatus.PENDING));
        stats.put("totalOrders", orderRepository.count());
        
        double totalRevenue = orderService.getAllOrders().stream()
            .mapToDouble(Order::getTotal)
            .sum();
        stats.put("totalRevenue", totalRevenue);
        
        return ResponseEntity.ok(stats);
    }
}