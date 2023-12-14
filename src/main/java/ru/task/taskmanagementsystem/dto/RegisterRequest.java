package ru.task.taskmanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {

    @NotBlank(message = "Имя не должно быть пустым")
    private String name;

    @Email(message = "Не верный формат email-адреса")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}
