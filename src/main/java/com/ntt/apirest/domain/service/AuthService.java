package com.ntt.apirest.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ntt.apirest.domain.classes.AuthResponse;
import com.ntt.apirest.domain.classes.LogginRequest;
import com.ntt.apirest.domain.dto.UserRequestDto;
import com.ntt.apirest.domain.dto.UserResponseDto;
import com.ntt.apirest.domain.errors.RegistrationException;
import com.ntt.apirest.enums.Role;
import com.ntt.apirest.models.Phone;
import com.ntt.apirest.models.User;
import com.ntt.apirest.persistence.repository.UserRepository;
import com.ntt.apirest.util.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
	private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    private final LocalDateTime now = LocalDateTime.now();
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Value("${registration.password-regex}")
    private String passwordRegex;

    public AuthResponse login(LogginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getCorreo(), loginRequest.getPassword()));
        User user = userRepository.getUserByCorreo(loginRequest.getCorreo()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public UserResponseDto register(UserRequestDto userRequestDto) throws RegistrationException {
        try {
            Optional<User> existingUser = userRepository.getUserByCorreo(userRequestDto.getCorreo());
            if (existingUser.isPresent()) throw new RegistrationException("El correo ya ha sido registrado");
            if (!userRequestDto.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.cl$")) throw new RegistrationException("Formato de corre no válido");
            if (!userRequestDto.getPassword().matches(passwordRegex)) throw new RegistrationException("Formato de contraseña no válido");
            // if (!userRequestDto.getCorreo().matches("^[a-zA-Z0-9._-]+@dominio\\.cl$")) throw new RegistrationException("Formato de corre no valido");

            User user = UserMapper.INSTANCE.userRequestDtoToUser(userRequestDto);
            user.setCreado(now);
            user.setModificado(now);
            user.setUltimoLogin(now);
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            List<Phone> phones = userRequestDto.getTelefonos().stream()
                .map(phoneRequestDto -> {
                    Phone phone = new Phone();
                    phone.setNumero(phoneRequestDto.getNumero());
                    phone.setCodigoCiudad(phoneRequestDto.getCodigoCiudad());
                    phone.setCodigoPais(phoneRequestDto.getCodigoPais());
                    phone.setCreado(now);
                    phone.setModificado(now);
                    phone.setUser(user);
                    return phone;
                })
            .collect(Collectors.toList());
            user.setTelefonos(phones);

            User createUser = userRepository.saveUser(user);
            UserResponseDto userResponseDto = UserMapper.INSTANCE.userToUserResponseDto(createUser);
            userResponseDto.setToken(jwtService.getToken(createUser));
            
            return userResponseDto;

        } catch (RegistrationException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el usuario", e);
        }
    }

}
