package com.mbp.mediBook.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mbp.mediBook.model.Medicine;

@Repository
public interface MedicineRepository extends MongoRepository<Medicine, String> {
    
    List<Medicine> findByStoreId(String storeId);
    
    List<Medicine> findByCategory(String category);
    
    List<Medicine> findByNameContainingIgnoreCase(String name);
    
    List<Medicine> findByStoreIdAndInStock(String storeId, boolean inStock);
    
    @Query("{'$or': [{'name': {'$regex': ?0, '$options': 'i'}}, {'category': {'$regex': ?0, '$options': 'i'}}]}")
    List<Medicine> searchMedicines(String keyword);
    
    List<Medicine> findByStoreIdAndStockLessThan(String storeId, int stock);
    
    long countByStoreId(String storeId);
}