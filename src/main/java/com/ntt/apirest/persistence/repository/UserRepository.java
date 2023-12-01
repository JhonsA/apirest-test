package com.ntt.apirest.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ntt.apirest.models.User;
import com.ntt.apirest.persistence.dao.UserDao;

@Repository
public class UserRepository {

    @Autowired
	private UserDao userDao;

    public  List<User> getUsers() {
        return userDao.findAll();
	}

    public Optional<User> getUserById(Long userId) {
        return userDao.findById(userId);
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }

    public void deleteUserById(Long userId) {
        userDao.deleteById(userId);
    }
    
}
