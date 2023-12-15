package ru.task.taskmanagementsystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.task.taskmanagementsystem.dto.TaskDto;
import ru.task.taskmanagementsystem.entity.Task;
import ru.task.taskmanagementsystem.entity.User;
import ru.task.taskmanagementsystem.exception.TaskServiceException;
import ru.task.taskmanagementsystem.exception.UserNotFoundException;
import ru.task.taskmanagementsystem.mapper.TaskMapper;
import ru.task.taskmanagementsystem.repository.TaskRepository;
import ru.task.taskmanagementsystem.repository.UserRepository;
import ru.task.taskmanagementsystem.service.TaskService;

import java.util.List;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        log.info("Got request for task by id#{}", id);
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskServiceException("Task with id#" + id + " not found"));
    }

    @Transactional
    public TaskDto createTask(TaskDto taskDto) {
        log.info("Got taskDto for create task {}", taskDto);
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь не найден по email:" + userEmail)
        );
        taskDto.setAuthorId(user.getId());
        Task taskForCreate = taskMapper.taskDtoToTask(taskDto);
        Task createdTask = taskRepository.save(taskForCreate);
        log.info("Created task: {}", createdTask);
        return taskMapper.taskToTaskDto(createdTask);
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        taskRepository.findById(id)
                .orElseThrow(() -> new TaskServiceException("Task with id#" + id + " not found"));

        taskDto.setId(id);
        Task updatingTask = taskMapper.taskDtoToTask(taskDto);
        log.info("Updating task with new data: {}", updatingTask);
        Task updatedTask = taskRepository.save(updatingTask);
        log.info("Updated task: {}", updatedTask);
        return taskMapper.taskToTaskDto(updatedTask);
    }

    @Transactional
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}
