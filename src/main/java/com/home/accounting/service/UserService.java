package com.home.accounting.service;

import com.home.accounting.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void deleteUser(User user);

    void deleteUserById(long id);

    User findUserById(long id);

    User findUserByName(String name);

    List<User> listAllUsers();

    void editUser(User user);

    boolean isUserUniqueLogin(User user);

    boolean isUserUniqueLogin(String login, long id);

    boolean isUserUniqueEmail(User user);

    boolean isUserUniqueEmail(String email, long id);

    boolean isUserUnique(long id);
}
