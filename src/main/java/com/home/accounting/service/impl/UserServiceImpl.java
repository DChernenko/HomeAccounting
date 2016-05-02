package com.home.accounting.service.impl;

import com.home.accounting.entity.User;
import com.home.accounting.repository.UserRepository;
import com.home.accounting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
    public void deleteUserById(long id) {
        userRepository.delete(id);
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findUserByLogin(name);
    }

    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void editUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public boolean isUserUniqueLogin(User user) {
        User userFindLogin = userRepository.findUserByLogin(user.getLogin());
        if (userFindLogin != null)
            if (user.getLogin().equals(userFindLogin.getLogin())) return true;

        return false;
    }

    @Override
    public boolean isUserUniqueLogin(String login, long id) {
        User user = userRepository.getOne(id);
        return ((user.getLogin() != login));
    }


    @Override
    public boolean isUserUniqueEmail(User user) {
        User userFindEmail = userRepository.findUserByEmail(user.getEmail());
        if (userFindEmail != null)
            if (userFindEmail != null || user.getEmail().equals(userFindEmail.getEmail())) return true;
        return false;
    }

    @Override
    public boolean isUserUniqueEmail(String email, long id) {
        User user = userRepository.getOne(id);
        return ((user.getEmail() != email));
    }


    @Override
    public boolean isUserUnique(long id) {
        User user = userRepository.getOne(id);
        if (user != null) return true;
        return false;
    }
}
