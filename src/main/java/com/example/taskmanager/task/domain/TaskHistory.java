package com.example.taskmanager.task.domain;

import com.example.taskmanager.task.domain.Task;
import com.example.taskmanager.task.domain.TaskStatus;
import com.example.taskmanager.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "task_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus oldStatus;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus newStatus;
    @ManyToOne
    @JoinColumn(name = "changed_by", nullable = false)
    private User changedBy;
    @Column(nullable = false, updatable = false)
    private LocalDateTime changedAt = LocalDateTime.now();
    private String reason;
}
