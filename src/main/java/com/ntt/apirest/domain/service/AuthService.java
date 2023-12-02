package com.ntt.apirest.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntt.apirest.domain.classes.AuthResponse;
import com.ntt.apirest.domain.classes.LogginRequest;
import com.ntt.apirest.domain.dto.UserRequestDto;
import com.ntt.apirest.domain.dto.UserResponseDto;
import com.ntt.apirest.models.User;
import com.ntt.apirest.persistence.repository.UserRepository;
import com.ntt.apirest.util.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
	private UserRepository userRepository;

    private final LocalDateTime now = LocalDateTime.now();

    public AuthResponse login(LogginRequest loginRequest) {
        return null;
    }

    public UserResponseDto register(UserRequestDto userRequestDto) {
        try {
            User user = UserMapper.INSTANCE.userRequestDtoToUser(userRequestDto);
            user.setCreado(now);
            user.setModificado(now);
            user.setUltimoLogin(now);
            user.setActivo(true);

            User createUser = userRepository.saveUser(user);
            return UserMapper.INSTANCE.userToUserResponseDto(userRepository.saveUser(createUser));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el usuario", e);
        }
    }

}
