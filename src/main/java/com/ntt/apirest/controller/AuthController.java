package com.ntt.apirest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntt.apirest.domain.classes.AuthResponse;
import com.ntt.apirest.domain.classes.LogginRequest;
import com.ntt.apirest.domain.dto.UserRequestDto;
import com.ntt.apirest.domain.dto.UserResponseDto;
import com.ntt.apirest.domain.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "AUTH CONTROLLER", description = "Auth operations")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    
    /**
     * Method: POST.
     * Description: Login user.
     */
    @Operation(description = "Login", summary = "AUTH CONTROLLER")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LogginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    /**
     * Method: POST.
     * Description: Register user.
     */
    @Operation(description = "Register", summary = "AUTH CONTROLLER")
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequest) {
        return ResponseEntity.ok(authService.register(userRequest));
    }

}
