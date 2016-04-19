package com.home.accounting.service;

import com.home.accounting.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void deleteUser(User user);

    User findUserId(long id);

    List<User> getAllUsers();

    void editUser(User user);
}
