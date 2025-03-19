package com.example.taskmanager.task.service;

import com.example.taskmanager.task.domain.*;
import com.example.taskmanager.user.domain.User;
import com.example.taskmanager.user.domain.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskHistoryRepository taskHistoryRepository;
    private final CategoryRepository categoryRepository;

    private TaskDTO convertToDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().toString(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getOwner().getId(),
                task.getCategory() != null ? task.getCategory().getId() : null,
                task.getDeadline()
        );
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Task not found"));
        return convertToDTO(task);
    }

    @Override
    public TaskDTO createTask(TaskCreateDTO taskCreateDTO) {
        User user = userRepository.findById(taskCreateDTO.getAssignedUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Task task = new Task();
        task.setTitle(taskCreateDTO.getTitle());
        task.setDescription(taskCreateDTO.getDescription());
        task.setStatus(TaskStatus.TODO);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setOwner(user);

        if (taskCreateDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(taskCreateDTO.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));
            task.setCategory(category);
        }

        task.setDeadline(taskCreateDTO.getDeadline());

        Task savedTask = taskRepository.save(task);
        return convertToDTO(savedTask);
    }

    @Override
    public List<TaskDTO> getOverdueTasks() {
        LocalDateTime now = LocalDateTime.now();
        return taskRepository.findAll().stream()
                .filter(task -> task.getDeadline() != null && task.getDeadline().isBefore(now))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO updateTask(Long taskId, TaskUpdateDTO taskUpdateDTO) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        if (task.getStatus() == taskUpdateDTO.getNewStatus()) {
            throw new IllegalArgumentException("Task is already in this status");
        }

        User user = userRepository.findById(taskUpdateDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        TaskHistory history = new TaskHistory();
        history.setTask(task);
        history.setOldStatus(task.getStatus());
        history.setNewStatus(taskUpdateDTO.getNewStatus());
        history.setChangedBy(user);
        history.setChangedAt(LocalDateTime.now());
        history.setReason(taskUpdateDTO.getReason());

        taskHistoryRepository.save(history);

        task.setStatus(taskUpdateDTO.getNewStatus());
        task.setUpdatedAt(LocalDateTime.now());

        Task updatedTask = taskRepository.save(task);
        return convertToDTO(updatedTask);
    }

    @Override
    public TaskDTO deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Task not found"));
        taskRepository.delete(task);
        return convertToDTO(task);
    }
    
}
