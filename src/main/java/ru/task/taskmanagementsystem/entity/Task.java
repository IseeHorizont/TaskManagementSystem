package ru.task.taskmanagementsystem.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Task {

    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    // todo maybe need @ManyToOne
    private Long authorId;

    // todo maybe need @ManyToOne
    private Long executorId;
}
