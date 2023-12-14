package ru.task.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.task.taskmanagementsystem.constant.TaskPriority;
import ru.task.taskmanagementsystem.constant.TaskStatus;


@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.WAIT;

    @Column(name = "priority")
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private TaskPriority priority = TaskPriority.MEDIUM;

    @Column(name = "author_id")
    private Long authorId;          // todo maybe need @ManyToOne

    @Column(name = "executor_id")
    private Long executorId;        // todo maybe need @ManyToOne
}
