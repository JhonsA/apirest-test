package com.ntt.apirest.util.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ntt.apirest.domain.dto.UserRequestDto;
import com.ntt.apirest.domain.dto.UserResponseDto;
import com.ntt.apirest.models.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "creado", target = "creado")
    @Mapping(source = "modificado", target = "modificado")
    @Mapping(source = "ultimoLogin", target = "ultimoLogin")
    @Mapping(target = "token", ignore = true)
    @Mapping(source = "activo", target = "activo")
    UserResponseDto userToUserResponseDto(User user);
    
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "password", target = "password")
    @Mapping(target = "telefonos", ignore = true)
    User userRequestDtoToUser(UserRequestDto userRequest);

}