package ru.task.taskmanagementsystem.dto;

import jakarta.validation.constraints.Size;
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
    @Size(min = 5, message = "Минимально допустимая длина пароля: 5 символов")
    private String password;
}
