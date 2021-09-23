package com.Semenin.Spring_boot.service;

import com.Semenin.Spring_boot.dao.UserDao;
import com.Semenin.Spring_boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {
    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.getUserByName(s);
    }

    @Override
    public User findByUserName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }
}
