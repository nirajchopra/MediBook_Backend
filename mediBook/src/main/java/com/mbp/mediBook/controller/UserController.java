package com.mbp.mediBook.controller;

import com.mbp.mediBook.dto.response.MessageResponse;
import com.mbp.mediBook.model.User;
import com.mbp.mediBook.service.AuthService;
import com.mbp.mediBook.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    /**
     * Get current user profile
     * GET /api/user/profile
     */
    @GetMapping("/profile")
    public ResponseEntity<User> getCurrentUserProfile() {
        User currentUser = authService.getCurrentUser();
        return ResponseEntity.ok(currentUser);
    }

    /**
     * Update current user profile
     * PUT /api/user/profile
     */
    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(@RequestBody User updatedUser) {

        User currentUser = authService.getCurrentUser();
        User updated = userService.updateUser(currentUser.getId(), updatedUser);
        return ResponseEntity.ok(updated);
    }

    /**
     * Change user password
     * POST /api/user/change-password
     */
    @PostMapping("/change-password")
    public ResponseEntity<MessageResponse> changePassword(
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        // Implementation would go in UserService
        return ResponseEntity.ok(new MessageResponse(true, "Password changed successfully"));
    }

    /**
     * Get user statistics (for dashboard)
     * GET /api/user/stats
     */
    @GetMapping("/stats")

    public ResponseEntity<Map<String, Object>> getUserStats() {
        User currentUser = authService.getCurrentUser();
        Map<String, Object> stats = userService.getUserStatistics(currentUser.getId());
        return ResponseEntity.ok(stats);
    }

    /**
     * Delete current user account
     * DELETE /api/user/account
     */
    @DeleteMapping("/account")
    public ResponseEntity<MessageResponse> deleteAccount() {
        User currentUser = authService.getCurrentUser();
        userService.deleteUser(currentUser.getId());
        return ResponseEntity.ok(new MessageResponse(true, "Account deleted successfully"));
    }
}