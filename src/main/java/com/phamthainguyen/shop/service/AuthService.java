package com.phamthainguyen.shop.service;

import com.phamthainguyen.shop.dto.auth.AuthResponse;
import com.phamthainguyen.shop.dto.auth.LoginRequest;
import com.phamthainguyen.shop.dto.auth.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    boolean isEmail(String email);
}
