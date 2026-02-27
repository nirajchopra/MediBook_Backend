package com.mbp.mediBook.service;

import com.mbp.mediBook.dto.request.LoginRequest;
import com.mbp.mediBook.dto.request.RegisterRequest;
import com.mbp.mediBook.dto.response.AuthResponse;
import com.mbp.mediBook.model.User;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    void register(RegisterRequest request);
    User getCurrentUser();
}