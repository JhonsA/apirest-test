package com.ntt.apirest.domain.service;

import org.springframework.stereotype.Service;

import com.ntt.apirest.domain.classes.AuthResponse;
import com.ntt.apirest.domain.classes.LogginRequest;
import com.ntt.apirest.domain.classes.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    public AuthResponse login(LogginRequest loginRequest) {
        return null;
    }

    public AuthResponse register(User userRequest) {
        return null;
    }

}
