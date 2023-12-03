package com.ntt.apirest.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ntt.apirest.domain.classes.UserUpdateRequest;
import com.ntt.apirest.domain.dto.UserRequestDto;
import com.ntt.apirest.domain.dto.UserResponseDto;
import com.ntt.apirest.domain.errors.RegistrationException;
import com.ntt.apirest.domain.errors.UserNotFoundException;
import com.ntt.apirest.enums.Role;
import com.ntt.apirest.models.Phone;
import com.ntt.apirest.models.User;
import com.ntt.apirest.persistence.repository.UserRepository;
import com.ntt.apirest.util.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
	private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Value("${registration.password-regex}")
    private String passwordRegex;

    private final LocalDateTime now = LocalDateTime.now();
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        try {
            return userRepository.getUsers();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener usuarios", e);
        }
        
    }

    public User getUserById(Long userId) {
        try {
            Optional<User> optUser = userRepository.getUserById(userId);
            return optUser.get();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el usuario por ID", e);
        }
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) throws RegistrationException {
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

            String token = jwtService.getToken(user);
            user.setToken(token);

            User createUser = userRepository.saveUser(user);

            UserResponseDto userResponseDto = UserMapper.INSTANCE.userToUserResponseDto(createUser);
            userResponseDto.setToken(token);
            
            return userResponseDto;

        } catch (RegistrationException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el usuario", e);
        }
    }

    public User updateUser(Long userId, UserUpdateRequest userUpdate) throws UserNotFoundException {
        try {
            Optional<User> existingUser = userRepository.getUserById(userId);
            if (existingUser.isEmpty()) throw new UserNotFoundException("El usuario ID: " + userId + " no se encuentra registrado");

            User existingUserObj = existingUser.get();
            existingUserObj.setNombre(userUpdate.getNombre());
            existingUserObj.setCorreo(userUpdate.getCorreo());
            existingUserObj.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
            existingUserObj.setModificado(LocalDateTime.now());

            return userRepository.saveUser(existingUserObj);
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el usuario", e);
        }
    }

    public void deleteUserById(Long userId) {
        try {
            Optional<User> existingUser = userRepository.getUserById(userId);
            if (existingUser.isEmpty()) throw new UserNotFoundException("El usuario ID: " + userId + " no se encuentra registrado");

            userRepository.deleteUserById(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el usuario por ID", e);
        }
    }

}
