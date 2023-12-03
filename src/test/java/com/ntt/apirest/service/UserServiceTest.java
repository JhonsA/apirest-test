package com.ntt.apirest.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import com.ntt.apirest.domain.dto.PhoneRequestDto;
import com.ntt.apirest.domain.dto.UserRequestDto;
import com.ntt.apirest.domain.dto.UserResponseDto;
import com.ntt.apirest.domain.errors.RegistrationException;
import com.ntt.apirest.domain.service.JwtService;
import com.ntt.apirest.domain.service.UserService;
import com.ntt.apirest.enums.Role;
import com.ntt.apirest.models.Phone;
import com.ntt.apirest.models.User;
import com.ntt.apirest.persistence.repository.UserRepository;

@SpringBootTest
@ComponentScan(basePackages = "com.ntt.apirest")
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    private User user;
    private Phone phone;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        phone = new Phone();
        phone.setId(1L);
        phone.setNumero("12345678");
        phone.setCodigoCiudad("123");
        phone.setCodigoPais("123");
        phone.setCreado(java.time.LocalDateTime.now());
        phone.setModificado(java.time.LocalDateTime.now());
        phone.setActivo(true);

        user = new User();
        user.setId(1L);
        user.setNombre("Jhons Albornoz");
        user.setCorreo("jhons.albornoz.araya@gmail.cl");
        user.setPassword("123456789");
        user.setTelefonos(Arrays.asList(phone));
        user.setCreado(java.time.LocalDateTime.now());
        user.setModificado(java.time.LocalDateTime.now());
        user.setUltimoLogin(java.time.LocalDateTime.now());
        user.setActivo(true);
        user.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaG9ucy5hbGJvcm5vei5hcmF5YUBnbWFpbC5jbCIsImlhdCI6MTcwMTY0Mjk2MywiZXhwIjoxNzAxNjQ0NDAzfQ.7N04p2dv4UA6pnpRLJMUXOhSLuP1zWgK6464HtUjISk");
        user.setRole(Role.USER);
    }

    @Test
    void testGetAllUsers() {
        // Configuración del repositorio simulado
        when(userRepository.getUsers()).thenReturn(Arrays.asList(user));

        // Ejecutar el método bajo prueba
        List<User> result = userService.getAllUsers();

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
    }

    @Test
    void testGetUserById() {
        // Configuración del repositorio simulado
        when(userRepository.getUserById(1L)).thenReturn(Optional.of(user));

        // Ejecutar el método bajo prueba
        User result = userService.getUserById(1L);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(user, result);
    }
    
}
