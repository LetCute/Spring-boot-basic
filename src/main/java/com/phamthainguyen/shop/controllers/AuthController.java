package com.phamthainguyen.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phamthainguyen.shop.dto.auth.AuthResponse;
import com.phamthainguyen.shop.dto.auth.LoginRequest;
import com.phamthainguyen.shop.dto.auth.RegisterRequest;
import com.phamthainguyen.shop.service.AuthServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @PostMapping("register")
    public ResponseEntity<AuthResponse> postMethodName(@RequestBody RegisterRequest register) {
        return ResponseEntity.ok(authService.register(register));
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> postMethodName(@RequestBody LoginRequest login) {
        return ResponseEntity.ok(authService.login(login));

    }

}
