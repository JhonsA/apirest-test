package com.ntt.apirest.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntt.apirest.domain.dto.UserDto;
import com.ntt.apirest.models.User;
import com.ntt.apirest.persistence.repository.UserRepository;
import com.ntt.apirest.util.mappers.UserMapper;

@Service
public class UserService {

    @Autowired
	private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(Long userId) {
        try {
            Optional<User> optUser = userRepository.getUserById(userId);
            return optUser.get();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el usuario por ID", e);
        }
    }

    public UserDto createUser(User user) {
        try {
            User createUser = userRepository.saveUser(user);
            return UserMapper.INSTANCE.userToUserDto(userRepository.saveUser(createUser));
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
