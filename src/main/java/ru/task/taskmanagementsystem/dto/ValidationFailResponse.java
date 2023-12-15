package ru.task.taskmanagementsystem.dto;

import java.util.List;


public record ValidationFailResponse(List<ValidationFailCause> validationFails) {
}
