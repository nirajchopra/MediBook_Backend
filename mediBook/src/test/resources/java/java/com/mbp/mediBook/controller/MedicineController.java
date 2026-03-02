package com.mbp.mediBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.mbp.mediBook.dto.request.MedicineRequest;
import com.mbp.mediBook.dto.response.MessageResponse;
import com.mbp.mediBook.model.Medicine;
import com.mbp.mediBook.service.MedicineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medicines")

@CrossOrigin(origins = "${cors.allowed-origins}")
public class MedicineController {

    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicine(@PathVariable String id) {
        return ResponseEntity.ok(medicineService.getMedicineById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Medicine>> searchMedicines(@RequestParam String keyword) {
        return ResponseEntity.ok(medicineService.searchMedicines(keyword));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Medicine>> getMedicinesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(medicineService.getMedicinesByCategory(category));
    }

    @PostMapping
    @PreAuthorize("hasRole('STORE')")
    public ResponseEntity<Medicine> addMedicine(@Valid @RequestBody MedicineRequest request) {
        return ResponseEntity.ok(medicineService.addMedicine(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('STORE')")
    public ResponseEntity<Medicine> updateMedicine(
            @PathVariable String id,
            @Valid @RequestBody MedicineRequest request) {
        return ResponseEntity.ok(medicineService.updateMedicine(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('STORE')")
    public ResponseEntity<MessageResponse> deleteMedicine(@PathVariable String id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok(new MessageResponse(true, "Medicine deleted successfully"));
    }
}