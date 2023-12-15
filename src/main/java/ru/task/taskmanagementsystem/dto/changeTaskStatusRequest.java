package ru.task.taskmanagementsystem.dto;

import ru.task.taskmanagementsystem.constant.TaskStatus;

public record changeTaskStatusRequest(TaskStatus newStatus) {
}
