package com.ntt.apirest.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntt.apirest.domain.dto.UserRequestDto;
import com.ntt.apirest.domain.dto.UserResponseDto;
import com.ntt.apirest.models.Phone;
import com.ntt.apirest.models.User;
import com.ntt.apirest.persistence.repository.UserRepository;
import com.ntt.apirest.util.mappers.UserMapper;

@Service
public class UserService {

    @Autowired
	private UserRepository userRepository;

    private final LocalDateTime now = LocalDateTime.now();

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

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        try {
            User user = UserMapper.INSTANCE.userRequestDtoToUser(userRequestDto);
            user.setCreado(now);
            user.setModificado(now);
            user.setUltimoLogin(now);
            user.setActivo(true);

            List<Phone> phones = userRequestDto.getTelefonos().stream()
                .map(phoneRequestDto -> {
                    Phone phone = new Phone();
                    phone.setNumero(phoneRequestDto.getNumero());
                    phone.setCodigoCiudad(phoneRequestDto.getCodigoCiudad());
                    phone.setCodigoPais(phoneRequestDto.getCodigoPais());
                    phone.setCreado(now);
                    phone.setModificado(now);
                    phone.setActivo(true);
                    phone.setUser(user);
                    return phone;
                })
            .collect(Collectors.toList());
            user.setTelefonos(phones);

            User createUser = userRepository.saveUser(user);
            return UserMapper.INSTANCE.userToUserResponseDto(userRepository.saveUser(createUser));
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el usuario", e);
        }
    }

    public User updateUser(Long userId, User user) {
        try {
            return userRepository.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el usuario", e);
        }
    }

    public void deleteUserById(Long userId) {
        try {
            userRepository.deleteUserById(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el usuario por ID", e);
        }
    }

}
