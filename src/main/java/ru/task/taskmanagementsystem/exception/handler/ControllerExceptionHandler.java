package ru.task.taskmanagementsystem.exception.handler;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.task.taskmanagementsystem.dto.ErrorResponse;
import ru.task.taskmanagementsystem.exception.RegistrationException;
import ru.task.taskmanagementsystem.exception.TaskServiceException;
import ru.task.taskmanagementsystem.exception.UserNotFoundException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<ErrorResponse> handleUserServiceException(@NonNull final RegistrationException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.valueOf(403))
                .body(new ErrorResponse(createErrorMessage(exc)));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(@NonNull final UserNotFoundException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(createErrorMessage(exc)));
    }

    @ExceptionHandler(TaskServiceException.class)
    public ResponseEntity<ErrorResponse> handleTaskServiceException(@NonNull final TaskServiceException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(createErrorMessage(exc)));
    }

    private String createErrorMessage(Exception exception) {
        final String message = exception.getMessage();
        log.error(ExceptionHandlerUtils.buildErrorMessage(exception));
        return message;
    }
}
