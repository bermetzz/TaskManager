package com.example.taskmanager.task.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateDTO {
    @NotBlank
    private String title;
    private String description;
    @NotNull
    private Long assignedUserId;
    private Long categoryId;
    private LocalDateTime deadline;
}
