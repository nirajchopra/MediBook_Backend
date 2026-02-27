package com.mbp.mediBook.service;

import java.util.*;

import com.mbp.mediBook.dto.request.MedicineRequest;
import com.mbp.mediBook.model.Medicine;

public interface MedicineService {
    List<Medicine> getAllMedicines();
    Medicine getMedicineById(String id);
    List<Medicine> searchMedicines(String keyword);
    List<Medicine> getMedicinesByCategory(String category);
    List<Medicine> getStoreMedicines(String storeId);
    List<Medicine> getLowStockMedicines(String storeId);
    Medicine addMedicine(MedicineRequest request);
    Medicine updateMedicine(String id, MedicineRequest request);
    void deleteMedicine(String id);
    Medicine updateStock(String id, Integer newStock);
}