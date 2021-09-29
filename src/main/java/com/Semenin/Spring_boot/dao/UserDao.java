package com.Semenin.Spring_boot.dao;


import com.Semenin.Spring_boot.model.User;

import java.util.List;

public interface UserDao {

    User getUserByEmail(String email);

    User getUserByName(String name);

    List<User> getAllUsers();

    void add(User user);

    void removeUserById(long id);

    void update(User user);

    User getUserById(long id);

}
