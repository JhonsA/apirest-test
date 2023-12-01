package com.ntt.apirest.domain.classes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String nombre;
    String correo;
    String password;
    Telefonos telefonos;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Telefonos {
        String numero;
        String codigoCiudad;
        String codigoPais;
    }
}

