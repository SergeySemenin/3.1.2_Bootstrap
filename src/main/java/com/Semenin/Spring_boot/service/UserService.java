package com.Semenin.Spring_boot.service;

import com.Semenin.Spring_boot.model.User;

import java.util.List;

public interface UserService {
    User findByUserName(String name);

    List<User> getAllUsers();

    void removeUserById(long id);

    void add(User user);

    void update(User user);

    User getUserById(long id);
}
