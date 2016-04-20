package com.home.accounting.service.impl;

import com.home.accounting.entity.User;
import com.home.accounting.repository.UserRepository;
import com.home.accounting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findUserId(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void editUser(User user) {
        userRepository.saveAndFlush(user);
    }
}
