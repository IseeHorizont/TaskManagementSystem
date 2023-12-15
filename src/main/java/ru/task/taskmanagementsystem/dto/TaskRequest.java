package ru.task.taskmanagementsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    @NotBlank(message = "Заголовок задачи не должен быть пустым")
    private String title;

    @NotBlank(message = "Описание задачи не должно быть пустым")
    private String description;

    private String status;

    private String priority;

    @Min(value = 1, message = "Id автора задачи должно быть больше нуля")
    private Long authorId;

    private Long executorId;
}
