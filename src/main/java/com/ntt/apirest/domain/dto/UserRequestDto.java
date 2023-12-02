package com.ntt.apirest.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String nombre;
    private String correo;
    private String password;
    private List<PhoneRequestDto> telefonos;
}
