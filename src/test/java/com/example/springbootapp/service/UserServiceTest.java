package com.example.springbootapp.service;

import com.example.springbootapp.dto.UserRequest;
import com.example.springbootapp.entity.User;
import com.example.springbootapp.exception.ResourceAlreadyExistsException;
import com.example.springbootapp.exception.ResourceNotFoundException;
import com.example.springbootapp.mapper.UserMapper;
import com.example.springbootapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setActive(true);

        userRequest = new UserRequest();
        userRequest.setName("John Doe");
        userRequest.setEmail("john@example.com");
    }

    @Test
    void testCreateUser_Success() {
        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(false);
        when(userMapper.toEntity(userRequest)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toResponse(user)).thenReturn(null);

        assertDoesNotThrow(() -> userService.createUser(userRequest));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testCreateUser_EmailExists() {
        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(true);

        assertThrows(ResourceAlreadyExistsException.class, () -> userService.createUser(userRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testGetUserById_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toResponse(user)).thenReturn(null);

        assertDoesNotThrow(() -> userService.getUserById(1L));
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1L));
    }
}

