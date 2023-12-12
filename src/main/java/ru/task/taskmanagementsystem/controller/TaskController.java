package ru.task.taskmanagementsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.task.taskmanagementsystem.entity.Task;
import ru.task.taskmanagementsystem.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@Slf4j
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<Task> getTasks() {
        log.info("Got request for task list");
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        log.info("Got request for task by {}", id);
        return taskService.getTaskById(id);
    }
}
