package com.example.taskmanager.task.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long assignedUserId;
    private Long categoryId;
    private LocalDateTime deadline;
    public TaskDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
