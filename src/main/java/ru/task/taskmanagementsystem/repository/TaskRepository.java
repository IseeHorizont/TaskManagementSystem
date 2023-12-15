package ru.task.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.task.taskmanagementsystem.constant.TaskStatus;
import ru.task.taskmanagementsystem.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Query("UPDATE Task t SET t.status = :newStatus WHERE t.id = :taskId")
    int setTaskStatusById(TaskStatus newStatus, Long taskId);
}
