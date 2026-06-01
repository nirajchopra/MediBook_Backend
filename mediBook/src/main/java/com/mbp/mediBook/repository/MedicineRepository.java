package com.mbp.mediBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mbp.mediBook.model.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    
    List<Medicine> findByStoreId(Long storeId);
    
    List<Medicine> findByCategory(String category);
    
    List<Medicine> findByNameContainingIgnoreCase(String name);
    
    List<Medicine> findByStoreIdAndInStock(Long storeId, boolean inStock);
    
    @Query("{'$or': [{'name': {'$regex': ?0, '$options': 'i'}}, {'category': {'$regex': ?0, '$options': 'i'}}]}")
    List<Medicine> searchMedicines(String keyword);
    
    List<Medicine> findByStoreIdAndStockLessThan(Long storeId, int stock);
    
    long countByStoreId(Long storeId);
}