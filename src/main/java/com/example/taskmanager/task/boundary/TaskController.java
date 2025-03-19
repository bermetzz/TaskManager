package com.example.taskmanager.task.boundary;

import com.example.taskmanager.task.domain.TaskCreateDTO;
import com.example.taskmanager.task.domain.TaskDTO;
import com.example.taskmanager.task.domain.TaskUpdateDTO;
import com.example.taskmanager.task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name="Tasks", description = "Task Management")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Get all tasks", description = "returns all tasks in the system")
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskByID(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    @Operation(summary = "Create a task", description = "Creates a task and saves it in db")
    public TaskDTO createTask(@RequestBody @Valid TaskCreateDTO taskCreateDTO) {
        return taskService.createTask(taskCreateDTO);
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskUpdateDTO taskUpdateDTO) {
        return taskService.updateTask(id, taskUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public TaskDTO deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

    @GetMapping("/overdue")
    public List<TaskDTO> getOverdueTasks() {
        return taskService.getOverdueTasks();
    }
}
