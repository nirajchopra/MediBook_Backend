package com.mbp.mediBook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mbp.mediBook.model.Store;
import com.mbp.mediBook.model.enums.StoreStatus;

@Repository
public interface StoreRepository extends MongoRepository<Store, String> {
    
    Optional<Store> findByUserId(String userId);
    
    Optional<Store> findByStoreIdCode(String storeIdCode);
    
    List<Store> findByStatus(StoreStatus status);
    
    List<Store> findByCity(String city);
    
    Boolean existsByStoreIdCode(String storeIdCode);
    
    long countByStatus(StoreStatus status);
}