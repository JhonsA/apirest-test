package com.ntt.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntt.apirest.domain.classes.AuthResponse;
import com.ntt.apirest.domain.classes.LogginRequest;
import com.ntt.apirest.domain.dto.UserRequestDto;
import com.ntt.apirest.domain.dto.UserResponseDto;
import com.ntt.apirest.domain.errors.RegistrationException;
import com.ntt.apirest.domain.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "AUTH CONTROLLER", description = "Auth operations")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
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
     * @throws RegistrationException
     */
    @Operation(description = "Register", summary = "AUTH CONTROLLER")
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequest) throws RegistrationException {
        return ResponseEntity.ok(authService.register(userRequest));
    }

}
