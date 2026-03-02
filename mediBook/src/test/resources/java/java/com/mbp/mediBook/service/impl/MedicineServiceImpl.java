package com.mbp.mediBook.service.impl;

import com.mbp.mediBook.dto.request.MedicineRequest;
import com.mbp.mediBook.exception.BadRequestException;
import com.mbp.mediBook.exception.ResourceNotFoundException;
import com.mbp.mediBook.model.Medicine;
import com.mbp.mediBook.model.Store;
import com.mbp.mediBook.repository.MedicineRepository;
import com.mbp.mediBook.repository.StoreRepository;
import com.mbp.mediBook.service.AuthService;
import com.mbp.mediBook.service.MedicineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final StoreRepository storeRepository;
    private final AuthService authService;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, 
                               StoreRepository storeRepository,
                               AuthService authService) {
        this.medicineRepository = medicineRepository;
        this.storeRepository = storeRepository;
        this.authService = authService;
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine getMedicineById(String id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not found with id: " + id));
    }

    @Override
    public List<Medicine> searchMedicines(String keyword) {
        return medicineRepository.searchMedicines(keyword);
    }

    @Override
    public List<Medicine> getMedicinesByCategory(String category) {
        return medicineRepository.findByCategory(category);
    }

    @Override
    public List<Medicine> getStoreMedicines(String storeId) {
        return medicineRepository.findByStoreId(storeId);
    }

    @Override
    public List<Medicine> getLowStockMedicines(String storeId) {
        return medicineRepository.findByStoreIdAndStockLessThan(storeId, 10);
    }

    @Override
    @Transactional
    public Medicine addMedicine(MedicineRequest request) {
        var currentUser = authService.getCurrentUser();
        Store store = storeRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new BadRequestException("Store not found for user"));

        Medicine medicine = new Medicine();
        medicine.setStoreId(store.getId());
        medicine.setName(request.getName());
        medicine.setDescription(request.getDescription());
        medicine.setCategory(request.getCategory());
        medicine.setManufacturer(request.getManufacturer());
        medicine.setPrice(request.getPrice());
        medicine.setDiscountPrice(request.getDiscountPrice());
        medicine.setStock(request.getStock());
        medicine.setInStock(request.getStock() > 0);
        medicine.setPrescriptionRequired(request.isPrescriptionRequired());
        medicine.setDosage(request.getDosage());
        medicine.setImageUrl(request.getImageUrl());
        medicine.setExpiryDate(request.getExpiryDate());

        return medicineRepository.save(medicine);
    }

    @Override
    @Transactional
    public Medicine updateMedicine(String id, MedicineRequest request) {
        Medicine medicine = getMedicineById(id);

        var currentUser = authService.getCurrentUser();
        Store store = storeRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new BadRequestException("Store not found"));

        if (!medicine.getStoreId().equals(store.getId())) {
            throw new BadRequestException("Not authorized to update this medicine");
        }

        medicine.setName(request.getName());
        medicine.setDescription(request.getDescription());
        medicine.setCategory(request.getCategory());
        medicine.setManufacturer(request.getManufacturer());
        medicine.setPrice(request.getPrice());
        medicine.setDiscountPrice(request.getDiscountPrice());
        medicine.setStock(request.getStock());
        medicine.setInStock(request.getStock() > 0);
        medicine.setPrescriptionRequired(request.isPrescriptionRequired());
        medicine.setDosage(request.getDosage());
        medicine.setImageUrl(request.getImageUrl());
        medicine.setExpiryDate(request.getExpiryDate());

        return medicineRepository.save(medicine);
    }

    @Override
    @Transactional
    public void deleteMedicine(String id) {
        Medicine medicine = getMedicineById(id);

        var currentUser = authService.getCurrentUser();
        Store store = storeRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new BadRequestException("Store not found"));

        if (!medicine.getStoreId().equals(store.getId())) {
            throw new BadRequestException("Not authorized to delete this medicine");
        }

        medicineRepository.delete(medicine);
    }

    @Override
    @Transactional
    public Medicine updateStock(String id, Integer newStock) {
        Medicine medicine = getMedicineById(id);
        medicine.setStock(newStock);
        medicine.setInStock(newStock > 0);
        return medicineRepository.save(medicine);
    }
}