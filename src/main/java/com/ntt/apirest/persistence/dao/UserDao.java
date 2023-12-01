package com.ntt.apirest.persistence.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ntt.apirest.models.User;

public interface UserDao extends CrudRepository <User, Long> {
    List<User> findAll();

    Optional<User> findById(Long userId);

    User save(User user);

    void deleteById(Long userId);
}
