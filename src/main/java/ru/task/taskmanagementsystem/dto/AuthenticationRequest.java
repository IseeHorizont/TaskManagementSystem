package ru.task.taskmanagementsystem.dto;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
public class AuthenticationRequest {

    @Email(message = "Не верный формат email-адреса")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}
