package com.mbp.mediBook.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbp.mediBook.exception.BadRequestException;
import com.mbp.mediBook.exception.ResourceNotFoundException;
import com.mbp.mediBook.model.Order;
import com.mbp.mediBook.model.User;
import com.mbp.mediBook.model.enums.OrderStatus;
import com.mbp.mediBook.model.enums.Role;
import com.mbp.mediBook.repository.OrderRepository;
import com.mbp.mediBook.repository.UserRepository;
import com.mbp.mediBook.service.UserService;

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

        // Get user's order count
        long orderCount = orderRepository.findByUserId(userId).size();

        // Get user's total spent
        double totalSpent = orderRepository.findByUserId(userId).stream()
                .mapToDouble(Order::getTotal)
                .sum();

        // Get pending orders
        long pendingOrders = orderRepository.findByUserId(userId).stream()
                .filter(o -> o.getStatus() == OrderStatus.PENDING)
                .count();

        stats.put("totalOrders", orderCount);
        stats.put("totalSpent", totalSpent);
        stats.put("pendingOrders", pendingOrders);
        stats.put("accountCreated", userRepository.findById(userId)
                .map(User::getCreatedAt).orElse(null));

        return stats;
    }

    @Transactional
    public void changePassword(String userId, String oldPassword, String newPassword) {
        User user = getUserById(userId);

        // Verify old password
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadRequestException("Old password is incorrect");
        }

        // Set new password
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}