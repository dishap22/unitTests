package com.example.demo.services;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void registerUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()) == null) {
            userRepository.saveUser(user);
        } else {
            throw new IllegalArgumentException("User already exists");
        }
    }
}
