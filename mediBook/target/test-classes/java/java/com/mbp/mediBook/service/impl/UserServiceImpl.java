package com.mbp.mediBook.service.impl;

import com.mbp.mediBook.exception.BadRequestException;
import com.mbp.mediBook.exception.ResourceNotFoundException;
import com.mbp.mediBook.model.User;
import com.mbp.mediBook.model.enums.Role;
import com.mbp.mediBook.repository.UserRepository;
import com.mbp.mediBook.repository.OrderRepository;
import com.mbp.mediBook.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, OrderRepository orderRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(String id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
	}

	@Override
	public List<User> getUsersByRole(Role role) {
		return userRepository.findByRole(role);
	}

	@Override
	@Transactional
	public User updateUser(String id, User updatedUser) {
		User user = getUserById(id);

		user.setFullName(updatedUser.getFullName());
		user.setPhoneNumber(updatedUser.getPhoneNumber());
		user.setProfileImage(updatedUser.getProfileImage());

		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteUser(String id) {
		User user = getUserById(id);
		userRepository.delete(user);
	}

	@Override
	@Transactional
	public User toggleUserStatus(String id) {
		User user = getUserById(id);
		user.setActive(!user.isActive());
		return userRepository.save(user);
	}

	@Override
	public Map<String, Long> getUserStats() {
		Map<String, Long> stats = new HashMap<>();
		stats.put("totalUsers", userRepository.countByRole(Role.USER));
		stats.put("totalStores", userRepository.countByRole(Role.STORE));
		stats.put("totalAdmins", userRepository.countByRole(Role.ADMIN));
		stats.put("activeUsers", (long) userRepository.findByIsActive(true).size());
		return stats;
	}

	@Override
	public Map<String, Object> getUserStatistics(String userId) {
		Map<String, Object> stats = new HashMap<>();
		stats.put("totalOrders", 0);
		stats.put("totalSpent", 0.0);
		stats.put("pendingOrders", 0);
		return stats;
	}

	@Override
	@Transactional
	public void changePassword(String userId, String oldPassword, String newPassword) {
		User user = getUserById(userId);

		if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
			throw new BadRequestException("Old password is incorrect");
		}

		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}
}