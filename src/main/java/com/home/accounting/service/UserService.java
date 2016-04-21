package com.home.accounting.service;

import com.home.accounting.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void deleteUser(User user);

    User findUserById(long id);

    User findUserByName(String name);

    List<User> getAllUsers();

    void editUser(User user);
}
