package ru.task.taskmanagementsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.task.taskmanagementsystem.entity.Task;
import ru.task.taskmanagementsystem.repository.TaskRepository;
import ru.task.taskmanagementsystem.service.TaskService;

import java.util.List;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        log.info("Got request for task by id#{}", id);
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id#" + id + " not found")); // todo custom exception
    }
}
