package ru.task.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.task.taskmanagementsystem.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(
        value = "SELECT * FROM comment WHERE task_id = ?1",
        nativeQuery = true
    )
    List<Comment> findAllByTaskId(Long taskId);

    @Query(
        value = "SELECT * FROM comment WHERE executor_id = ?1",
        nativeQuery = true
    )
    List<Comment> findAllByExecutorId(Long executorId);
}
