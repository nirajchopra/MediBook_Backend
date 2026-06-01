package com.mbp.mediBook.service;

import java.util.*;

import com.mbp.mediBook.dto.request.MedicineRequest;
import com.mbp.mediBook.model.Medicine;

public interface MedicineService {
    List<Medicine> getAllMedicines();
    Medicine getMedicineById(Long id);
    List<Medicine> searchMedicines(String keyword);
    List<Medicine> getMedicinesByCategory(String category);
    List<Medicine> getStoreMedicines(Long storeId);
    List<Medicine> getLowStockMedicines(Long storeId);
    Medicine addMedicine(MedicineRequest request);
    Medicine updateMedicine(Long id, MedicineRequest request);
    void deleteMedicine(Long id);
    Medicine updateStock(Long id, Integer newStock);
}