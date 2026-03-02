package com.mbp.mediBook.service;

import java.util.List;

import com.mbp.mediBook.dto.request.StoreRequest;
import com.mbp.mediBook.model.Store;
import com.mbp.mediBook.model.enums.StoreStatus;

public interface StoreService {
    Store createStore(StoreRequest request);
    Store getStoreById(String id);
    Store getStoreByUserId(String userId);
    List<Store> getAllStores();
    List<Store> getStoresByStatus(StoreStatus status);
    Store updateStore(String id, StoreRequest request);
    Store approveStore(String id);
    Store rejectStore(String id);
}