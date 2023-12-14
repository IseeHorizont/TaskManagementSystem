package ru.task.taskmanagementsystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.task.taskmanagementsystem.dto.CommentDto;
import ru.task.taskmanagementsystem.entity.Comment;
import ru.task.taskmanagementsystem.mapper.CommentMapper;
import ru.task.taskmanagementsystem.repository.CommentRepository;
import ru.task.taskmanagementsystem.service.CommentService;

import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Transactional
    public CommentDto addNewComment(CommentDto commentDto) {
        log.info("Got comment for adding: {}", commentDto);
        Comment createdComment = commentRepository.save(commentMapper.commentDtoToComment(commentDto));
        log.info("Created new comment: {}", createdComment);
        return commentMapper.commentToCommentDto(createdComment);
    }

    @Transactional
    public List<CommentDto> getCommentsByTaskId(Long taskId) {
        log.info("Looking for comments by task's id#{}", taskId);
        List<Comment> foundList = commentRepository.findAllByTaskId(taskId);
        log.info("Found comments by task's id#{}: {}", taskId, foundList);
        return foundList.stream()
                .map(commentMapper::commentToCommentDto)
                .toList();
    }

    @Transactional
    public List<CommentDto> getCommentsByExecutorId(Long executorId) {//todo maybe duplicate with 'getCommentsByTaskId'?
        log.info("Looking for comments by executor's id#{}", executorId);
        List<Comment> foundList = commentRepository.findAllByExecutorId(executorId);
        log.info("Found comments by executor's id#{}: {}", executorId, foundList);
        return foundList.stream()
                .map(commentMapper::commentToCommentDto)
                .toList();
    }
}
