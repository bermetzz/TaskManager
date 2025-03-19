package com.example.taskmanager.task.service;


import com.example.taskmanager.task.domain.*;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long id);

    TaskDTO createTask(TaskCreateDTO taskCreateDTO);

    List<TaskDTO> getOverdueTasks();

    TaskDTO updateTask(Long taskId, TaskUpdateDTO taskUpdateDTO);

    TaskDTO deleteTask(Long id);
}
