package com.ntt.apirest.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private LocalDateTime creado;
    private LocalDateTime modificado;
    private LocalDateTime ultimoLogin;
    private String token;
    private boolean activo;
}