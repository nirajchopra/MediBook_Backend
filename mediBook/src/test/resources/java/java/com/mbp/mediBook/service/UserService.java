package com.mbp.mediBook.service;

import com.mbp.mediBook.model.User;
import com.mbp.mediBook.model.enums.Role;
import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(String id);
    List<User> getUsersByRole(Role role);
    User updateUser(String id, User user);
    void deleteUser(String id);
    User toggleUserStatus(String id);
    Map<String, Long> getUserStats();
    
    // New methods for UserController
    Map<String, Object> getUserStatistics(String userId);
    void changePassword(String userId, String oldPassword, String newPassword);
}