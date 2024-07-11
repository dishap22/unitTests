package com.example.demo.dao;

import com.example.demo.model.User;

public interface UserRepository {
    User findUserByUsername(String username);
    void saveUser(User user);
}

