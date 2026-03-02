package com.mbp.mediBook.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbp.mediBook.exception.ResourceNotFoundException;
import com.mbp.mediBook.model.User;
import com.mbp.mediBook.model.enums.Role;
import com.mbp.mediBook.repository.UserRepository;
import com.mbp.mediBook.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
    	this.userRepository = userRepository;
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
}