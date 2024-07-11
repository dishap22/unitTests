package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.dao.UserRepository;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUser() {
        User user = new User("name", "password");
        when(userRepository.findUserByUsername("name")).thenReturn(user);

        User foundUser = userService.findUser("name");
        assertEquals("name", foundUser.getUsername());
        assertEquals("password", foundUser.getPassword());
        verify(userRepository, times(1)).findUserByUsername("name");
    }

    @Test
    void testRegisterUser() {
        User user = new User("name", "password");
        when(userRepository.findUserByUsername("name")).thenReturn(null);

        userService.registerUser(user);
        verify(userRepository, times(1)).saveUser(user);
    }

    @Test
    void testRegisterUserAlreadyExists() {
        User user = new User("name", "password");
        when(userRepository.findUserByUsername("name")).thenReturn(user);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           userService.registerUser(new User("name", "password"));
        });

        assertEquals("User already exists", exception.getMessage());
        verify(userRepository, never()).saveUser(any(User.class));
    }
}
