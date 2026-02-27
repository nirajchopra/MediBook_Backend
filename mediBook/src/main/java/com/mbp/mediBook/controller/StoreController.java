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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbp.mediBook.dto.request.MedicineRequest;
import com.mbp.mediBook.dto.request.StoreRequest;
import com.mbp.mediBook.dto.response.MessageResponse;
import com.mbp.mediBook.model.Medicine;
import com.mbp.mediBook.model.Order;
import com.mbp.mediBook.model.Store;
import com.mbp.mediBook.service.AuthService;
import com.mbp.mediBook.service.MedicineService;
import com.mbp.mediBook.service.OrderService;
import com.mbp.mediBook.service.StoreService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.var;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
@PreAuthorize("hasRole('STORE')")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class StoreController {
    
    private final StoreService storeService;
    private final MedicineService medicineService;
    private final OrderService orderService;
    private final AuthService authService;
    
    @GetMapping("/profile")
    public ResponseEntity<Store> getStoreProfile() {
        var currentUser = authService.getCurrentUser();
        return ResponseEntity.ok(storeService.getStoreByUserId(currentUser.getId()));
    }
    
    @PostMapping("/register")
    public ResponseEntity<Store> registerStore(@Valid @RequestBody StoreRequest request) {
        return ResponseEntity.ok(storeService.createStore(request));
    }
    
    @GetMapping("/medicines")
    public ResponseEntity<List<Medicine>> getStoreMedicines() {
        var currentUser = authService.getCurrentUser();
        Store store = storeService.getStoreByUserId(currentUser.getId());
        return ResponseEntity.ok(medicineService.getStoreMedicines(store.getId()));
    }
    
    @PostMapping("/medicines")
    public ResponseEntity<Medicine> addMedicine(@Valid @RequestBody MedicineRequest request) {
        return ResponseEntity.ok(medicineService.addMedicine(request));
    }
    
    @PutMapping("/medicines/{id}")
    public ResponseEntity<Medicine> updateMedicine(
            @PathVariable String id,
            @Valid @RequestBody MedicineRequest request) {
        return ResponseEntity.ok(medicineService.updateMedicine(id, request));
    }
    
    @DeleteMapping("/medicines/{id}")
    public ResponseEntity<MessageResponse> deleteMedicine(@PathVariable String id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok(new MessageResponse(true, "Medicine deleted successfully"));
    }
    
    @PatchMapping("/medicines/{id}/stock")
    public ResponseEntity<Medicine> updateStock(
            @PathVariable String id,
            @RequestParam Integer stock) {
        return ResponseEntity.ok(medicineService.updateStock(id, stock));
    }
    
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getStoreOrders() {
        var currentUser = authService.getCurrentUser();
        Store store = storeService.getStoreByUserId(currentUser.getId());
        return ResponseEntity.ok(orderService.getStoreOrders(store.getId()));
    }
    
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        var currentUser = authService.getCurrentUser();
        Store store = storeService.getStoreByUserId(currentUser.getId());
        
        List<Medicine> medicines = medicineService.getStoreMedicines(store.getId());
        List<Medicine> lowStock = medicineService.getLowStockMedicines(store.getId());
        List<Order> orders = orderService.getStoreOrders(store.getId());
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalMedicines", medicines.size());
        stats.put("lowStock", lowStock.size());
        stats.put("totalOrders", orders.size());
        stats.put("pendingOrders", orders.stream()
            .filter(o -> o.getStatus().name().equals("PENDING")).count());
        
        return ResponseEntity.ok(stats);
    }
}