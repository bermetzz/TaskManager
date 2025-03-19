package com.example.taskmanager.task.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateDTO {
    private TaskStatus newStatus;
    private Long userId;
    private String reason;
    private Long categoryId;
}
