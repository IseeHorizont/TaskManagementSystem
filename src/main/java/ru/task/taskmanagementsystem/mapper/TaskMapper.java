package ru.task.taskmanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.task.taskmanagementsystem.dto.TaskDto;
import ru.task.taskmanagementsystem.dto.TaskRequest;
import ru.task.taskmanagementsystem.dto.TaskResponse;
import ru.task.taskmanagementsystem.entity.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto taskRequestToTaskDto(TaskRequest taskRequest);

    TaskResponse taskDtoToTaskResponse(TaskDto taskDto);

    Task taskDtoToTask(TaskDto taskDto);

    TaskDto taskToTaskDto(Task task);
}
