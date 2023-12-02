package com.ntt.apirest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDto {
    private String nombre;
    private String correo;
    private String password;
    private Telefonos telefonos;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Telefonos {
        private String numero;
        private String codigoCiudad;
        private String codigoPais;
    }
    
}
