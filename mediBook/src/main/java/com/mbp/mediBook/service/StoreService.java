package com.mbp.mediBook.service;

import java.util.List;

import com.mbp.mediBook.dto.request.StoreRequest;
import com.mbp.mediBook.model.Store;
import com.mbp.mediBook.model.enums.StoreStatus;

public interface StoreService {
    Store createStore(StoreRequest request);
    Store getStoreById(Long id);
    Store getStoreByUserId(Long userId);
    List<Store> getAllStores();
    List<Store> getStoresByStatus(StoreStatus status);
    Store updateStore(Long id, StoreRequest request);
    Store approveStore(Long id);
    Store rejectStore(Long id);
}