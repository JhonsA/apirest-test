package com.ntt.apirest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequestDto {
    private String numero;
    private String codigoCiudad;
    private String codigoPais;
}
