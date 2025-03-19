package com.example.taskmanager.user.service;

import com.example.taskmanager.user.domain.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
}
