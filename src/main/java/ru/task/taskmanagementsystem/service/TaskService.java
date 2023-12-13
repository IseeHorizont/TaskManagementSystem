package ru.task.taskmanagementsystem.service;

import ru.task.taskmanagementsystem.dto.TaskDto;
import ru.task.taskmanagementsystem.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();

    Task getTaskById(Long id);

    TaskDto createTask(TaskDto taskDto);
}
