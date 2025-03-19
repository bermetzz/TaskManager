package com.example.taskmanager.task.boundary;

import com.example.taskmanager.task.domain.TaskHistory;
import com.example.taskmanager.task.domain.TaskHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/task-history")
@RequiredArgsConstructor
public class TaskHistoryController {
    private final TaskHistoryRepository taskHistoryRepository;

    @GetMapping("/{taskId}")
    public ResponseEntity<List<TaskHistory>> getHistory(@PathVariable Long taskId) {
        List<TaskHistory> history = taskHistoryRepository.findByTaskId(taskId);
        return  ResponseEntity.ok(history);
    }
}
