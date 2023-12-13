package ru.task.taskmanagementsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.task.taskmanagementsystem.dto.TaskDto;
import ru.task.taskmanagementsystem.entity.Task;
import ru.task.taskmanagementsystem.exception.TaskServiceException;
import ru.task.taskmanagementsystem.mapper.TaskMapper;
import ru.task.taskmanagementsystem.repository.TaskRepository;
import ru.task.taskmanagementsystem.service.TaskService;

import java.util.List;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        log.info("Got request for task by id#{}", id);
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskServiceException("User with id#" + id + " not found")); // todo custom exception
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        log.info("Got taskDto for create task {}", taskDto);
        taskDto.setAuthorId(1L);// todo add author_id
        Task taskForCreate = taskMapper.taskDtoToTask(taskDto);
        Task createdTask = taskRepository.save(taskForCreate);
        log.info("Created task: {}", createdTask);
        return taskMapper.taskToTaskDto(createdTask);
    }
}
