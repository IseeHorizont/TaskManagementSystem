package ru.task.taskmanagementsystem.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.task.taskmanagementsystem.dto.TaskDto;
import ru.task.taskmanagementsystem.dto.TaskRequest;
import ru.task.taskmanagementsystem.dto.TaskResponse;
import ru.task.taskmanagementsystem.entity.Task;
import ru.task.taskmanagementsystem.mapper.TaskMapper;
import ru.task.taskmanagementsystem.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@Slf4j
@Validated
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/")
    public List<Task> getTasks() {
        log.info("Got request for task list");
        return taskService.getTasks();
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable("taskId") @Min(1) Long taskId) {
        log.info("Got request for task by {}", taskId);
        return taskService.getTaskById(taskId);
    }

    @PostMapping("/")
    public TaskResponse createTask(@Valid @RequestBody TaskRequest taskRequest) {
        log.info("Got request for create new task: {}", taskRequest);
        TaskDto taskDto = taskMapper.taskRequestToTaskDto(taskRequest);
        log.info("Mapped RequestTask to TaskDto: {}", taskDto);
        TaskDto createdTask = taskService.createTask(taskDto);
        log.info("Got created task from service: {}", createdTask);
        return taskMapper.taskDtoToTaskResponse(createdTask);
    }

    @PutMapping("/{taskId}")
    public TaskResponse updateTask(
            @PathVariable("taskId") @Min(1) Long taskId,
            @Valid @RequestBody TaskRequest taskRequest
    ) {
        log.info("Got request for update task with id#{}: {}", taskId, taskRequest);
        TaskDto taskDto = taskMapper.taskRequestToTaskDto(taskRequest);
        log.info("Mapped RequestTask to TaskDto: {}", taskDto);
        TaskDto updatedTask = taskService.updateTask(taskId, taskDto);
        log.info("Task updated: {}", updatedTask);
        return taskMapper.taskDtoToTaskResponse(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable("taskId") @Min(1) Long taskId) {
        log.info("Got request for delete task by id#{}", taskId);
        taskService.deleteTaskById(taskId);
        log.info("Task with id#{} deleted", taskId);
    }
}
