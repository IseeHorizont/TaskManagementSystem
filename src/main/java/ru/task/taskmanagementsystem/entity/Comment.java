package ru.task.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "comment")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "text")
    private String text;
}
