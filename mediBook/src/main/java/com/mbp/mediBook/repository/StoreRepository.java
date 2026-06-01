package com.mbp.mediBook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mbp.mediBook.model.Store;
import com.mbp.mediBook.model.enums.StoreStatus;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    
    Optional<Store> findByUserId(Long userId);
    
    Optional<Store> findByStoreIdCode(String storeIdCode);
    
    List<Store> findByStatus(StoreStatus status);

    Optional<Store> findById(Long storeId);
    
    List<Store> findByCity(String city);
    
    Boolean existsByStoreIdCode(String storeIdCode);
    
    long countByStatus(StoreStatus status);
}