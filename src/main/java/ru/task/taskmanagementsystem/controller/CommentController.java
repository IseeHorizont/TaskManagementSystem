package ru.task.taskmanagementsystem.controller;

import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.task.taskmanagementsystem.dto.CommentDto;
import ru.task.taskmanagementsystem.dto.CommentRequest;
import ru.task.taskmanagementsystem.dto.CommentResponse;
import ru.task.taskmanagementsystem.mapper.CommentMapper;
import ru.task.taskmanagementsystem.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@Slf4j
@Validated
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping("/")
    public CommentResponse createComment(@RequestBody CommentRequest commentRequest) {
        log.info("Got request for create new comment: {}", commentRequest);
        CommentDto commentDto = commentMapper.commentRequestToCommentDto(commentRequest);
        CommentDto createdComment = commentService.addNewComment(commentDto);
        log.info("Created new comment: {}", createdComment);
        return commentMapper.commentDtoToCommentResponse(createdComment);
    }

    @GetMapping("/{taskId}")
    public List<CommentResponse> getAllByTaskId(@PathVariable("taskId") @Min(1) Long taskId) {
        log.info("Looking for all comments by task's id#{}", taskId);
        List<CommentDto> foundComments = commentService.getCommentsByTaskId(taskId);
        log.info("Found comments by task id#{}: {}", taskId, foundComments);
        return foundComments.stream()
                .map(commentMapper::commentDtoToCommentResponse)
                .toList();
    }
}
