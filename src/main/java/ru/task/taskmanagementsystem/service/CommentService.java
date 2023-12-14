package ru.task.taskmanagementsystem.service;


import ru.task.taskmanagementsystem.dto.CommentDto;
import java.util.List;

public interface CommentService {

    CommentDto addNewComment(CommentDto commentDto);

    List<CommentDto> getCommentsByTaskId(Long taskId);
}
