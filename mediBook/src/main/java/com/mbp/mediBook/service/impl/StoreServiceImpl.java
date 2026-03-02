package com.mbp.mediBook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbp.mediBook.dto.request.StoreRequest;
import com.mbp.mediBook.exception.BadRequestException;
import com.mbp.mediBook.exception.ResourceNotFoundException;
import com.mbp.mediBook.model.Store;
import com.mbp.mediBook.model.enums.StoreStatus;
import com.mbp.mediBook.repository.StoreRepository;
import com.mbp.mediBook.service.AuthService;
import com.mbp.mediBook.service.StoreService;

import lombok.var;

@Service
public class StoreServiceImpl implements StoreService {

	private final StoreRepository storeRepository;
	private final AuthService authService;

	@Autowired
	public StoreServiceImpl(StoreRepository storeRepository, AuthService authService) {

		this.storeRepository = storeRepository;
		this.authService = authService;
	}

	@Override
	@Transactional
	public Store createStore(StoreRequest request) {
		var currentUser = authService.getCurrentUser();

		if (storeRepository.findByUserId(((Store) currentUser).getId()).isPresent()) {
			throw new BadRequestException("Store already registered for this user");
		}

		if (storeRepository.existsByStoreIdCode(request.getStoreIdCode())) {
			throw new BadRequestException("Store ID code already exists");
		}

		Store store = new Store();
		store.setUserId(((Store) currentUser).getId());
		store.setStoreIdCode(request.getStoreIdCode());
		store.setStoreName(request.getStoreName());
		store.setOwnerName(request.getOwnerName());
		store.setEmail(request.getEmail());
		store.setPhone(request.getPhone());
		store.setAddress(request.getAddress());
		store.setCity(request.getCity());
		store.setState(request.getState());
		store.setPincode(request.getPincode());
		store.setLicenseNumber(request.getLicenseNumber());
		store.setOpeningTime(request.getOpeningTime());
		store.setClosingTime(request.getClosingTime());
		store.setStatus(StoreStatus.PENDING);
		store.setActive(false);

		return storeRepository.save(store);
	}

	@Override
	public Store getStoreById(String id) {
		return storeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Store not found with id: " + id));
	}

	@Override
	public Store getStoreByUserId(String userId) {
		return storeRepository.findByUserId(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Store not found for user"));
	}

	@Override
	public List<Store> getAllStores() {
		return storeRepository.findAll();
	}

	@Override
	public List<Store> getStoresByStatus(StoreStatus status) {
		return storeRepository.findByStatus(status);
	}

	@Override
	@Transactional
	public Store updateStore(String id, StoreRequest request) {
		Store store = getStoreById(id);

		store.setStoreName(request.getStoreName());
		store.setOwnerName(request.getOwnerName());
		store.setEmail(request.getEmail());
		store.setPhone(request.getPhone());
		store.setAddress(request.getAddress());
		store.setCity(request.getCity());
		store.setState(request.getState());
		store.setPincode(request.getPincode());
		store.setLicenseNumber(request.getLicenseNumber());
		store.setOpeningTime(request.getOpeningTime());
		store.setClosingTime(request.getClosingTime());

		return storeRepository.save(store);
	}

	@Override
	@Transactional
	public Store approveStore(String id) {
		Store store = getStoreById(id);
		store.setStatus(StoreStatus.APPROVED);
		store.setActive(true);
		return storeRepository.save(store);
	}

	@Override
	@Transactional
	public Store rejectStore(String id) {
		Store store = getStoreById(id);
		store.setStatus(StoreStatus.REJECTED);
		store.setActive(false);
		return storeRepository.save(store);
	}
}