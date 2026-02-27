package com.mbp.mediBook.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbp.mediBook.dto.request.LoginRequest;
import com.mbp.mediBook.dto.request.RegisterRequest;
import com.mbp.mediBook.dto.response.AuthResponse;
import com.mbp.mediBook.exception.BadRequestException;
import com.mbp.mediBook.exception.ResourceNotFoundException;
import com.mbp.mediBook.model.User;
import com.mbp.mediBook.model.enums.Role;
import com.mbp.mediBook.repository.UserRepository;
import com.mbp.mediBook.security.CustomUserDetails;
import com.mbp.mediBook.security.JwtTokenProvider;
import com.mbp.mediBook.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    
    @Override
    @Transactional
    public AuthResponse login(LoginRequest request) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Generate JWT token
        String token = tokenProvider.generateToken(authentication);
        
        // Get user details
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userRepository.findById(userDetails.getId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        return new AuthResponse(
            token,
            user.getId(),
            user.getEmail(),
            user.getFullName(),
            user.getRole().name()
        );
    }
    
    @Override
    @Transactional
    public void register(RegisterRequest request) {
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already registered");
        }
        
        // Create new user
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setActive(true);
        
        userRepository.save(user);
    }
    
    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadRequestException("User not authenticated");
        }
        
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userRepository.findById(userDetails.getId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
