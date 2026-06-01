package com.mbp.mediBook.service;

import java.util.List;
import java.util.Map;

import com.mbp.mediBook.model.User;
import com.mbp.mediBook.model.enums.Role;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    List<User> getUsersByRole(Role role);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User toggleUserStatus(Long id);
    Map<String, Long> getUserStats();
    Map<String, Object> getUserStatistics(Long userId);
}
