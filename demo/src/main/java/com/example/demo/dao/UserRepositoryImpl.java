package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private Map<String, User> database = new HashMap<>();

    @Override
    public User findUserByUsername(String username) {
        return database.get(username);
    }

    @Override
    public void saveUser(User user) {
        database.put(user.getUsername(), user);
    }
}