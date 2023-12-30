package com.phamthainguyen.shop.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.phamthainguyen.shop.dto.auth.AuthResponse;
import com.phamthainguyen.shop.dto.auth.LoginRequest;
import com.phamthainguyen.shop.dto.auth.RegisterRequest;
import com.phamthainguyen.shop.entity.user.Role;
import com.phamthainguyen.shop.entity.user.User;
import com.phamthainguyen.shop.respository.UserRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRespository userRespository;

    private final PasswordEncoder passwordEncoder;

    private final JwtServiceImpl jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (isEmail(request.getEmail())) {
            return AuthResponse.builder().type("Exist").build();
        }
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRespository.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthResponse.builder().type("Register").token(jwt).build();
    }

    public AuthResponse login(LoginRequest request) {
        if (!isEmail(request.getEmail())) {
            return AuthResponse.builder().type("Fall").build();
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                    request.getPassword()));
            User user = userRespository.findByEmail(request.getEmail())
                    .orElseThrow(
                            () -> new RuntimeException("Không tìm thấy người dùng với email: " + request.getEmail()));
            String jwt = jwtService.generateToken(user);
            return AuthResponse.builder().type("Login").token(jwt).build();
        } catch (Exception e) {
            return AuthResponse.builder().type("Fall").build();
        }
    }

    public boolean isEmail(String email) {
        return userRespository.findByEmail(email).isPresent();
    }
}
