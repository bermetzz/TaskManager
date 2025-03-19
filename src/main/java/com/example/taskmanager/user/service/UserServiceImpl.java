package com.example.taskmanager.user.service;

import com.example.taskmanager.task.domain.Task;
import com.example.taskmanager.user.domain.User;
import com.example.taskmanager.user.domain.UserDTO;
import com.example.taskmanager.user.domain.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private UserDTO convertToDTO(User user) {
        List<Long> taskIds = user.getTasks().stream().map(Task::getId).collect(Collectors.toList());
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), taskIds);
    }
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found"));
        return convertToDTO(user);
    }

}
