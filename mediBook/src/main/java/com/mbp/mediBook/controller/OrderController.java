package com.mbp.mediBook.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbp.mediBook.dto.request.OrderRequest;
import com.mbp.mediBook.dto.response.MessageResponse;
import com.mbp.mediBook.model.Order;
import com.mbp.mediBook.model.enums.OrderStatus;
import com.mbp.mediBook.service.AuthService;
import com.mbp.mediBook.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.var;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "${cors.allowed-origins}")
public class OrderController {
    
    private final OrderService orderService;
    private final AuthService authService;
    
    @GetMapping
    public ResponseEntity<List<Order>> getUserOrders() {
        var currentUser = authService.getCurrentUser();
        return ResponseEntity.ok(orderService.getUserOrders(currentUser.getId()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }
    
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('STORE')")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable String id,
            @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> cancelOrder(@PathVariable String id) {
        orderService.cancelOrder(id);
        return ResponseEntity.ok(new MessageResponse(true, "Order cancelled successfully"));
    }
}