package ru.task.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.task.taskmanagementsystem.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
