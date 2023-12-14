package ru.task.taskmanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.task.taskmanagementsystem.dto.CommentDto;
import ru.task.taskmanagementsystem.dto.CommentRequest;
import ru.task.taskmanagementsystem.dto.CommentResponse;
import ru.task.taskmanagementsystem.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto commentRequestToCommentDto(CommentRequest commentRequest);

    CommentResponse commentDtoToCommentResponse(CommentDto commentDto);

    Comment commentDtoToComment(CommentDto commentDto);

    CommentDto commentToCommentDto(Comment comment);
}
