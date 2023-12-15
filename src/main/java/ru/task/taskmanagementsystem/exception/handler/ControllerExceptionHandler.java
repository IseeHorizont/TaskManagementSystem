package ru.task.taskmanagementsystem.exception.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.task.taskmanagementsystem.dto.ErrorResponse;
import ru.task.taskmanagementsystem.dto.ValidationFailResponse;
import ru.task.taskmanagementsystem.dto.ValidationFailCause;
import ru.task.taskmanagementsystem.exception.RegistrationException;
import ru.task.taskmanagementsystem.exception.TaskServiceException;
import ru.task.taskmanagementsystem.exception.UserNotFoundException;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<ErrorResponse> handleUserServiceException(
            @NonNull final RegistrationException exc
    ) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.valueOf(403))
                .body(new ErrorResponse(createErrorMessage(exc)));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            @NonNull final UserNotFoundException exc
    ) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(createErrorMessage(exc)));
    }

    @ExceptionHandler(TaskServiceException.class)
    public ResponseEntity<ErrorResponse> handleTaskServiceException(
            @NonNull final TaskServiceException exc
    ) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(createErrorMessage(exc)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationFailResponse> handleMethodArgumentNotValidException(
            @NonNull final MethodArgumentNotValidException exc
    ) {
        log.error(exc.getMessage());
        final List<ValidationFailCause> violations
                = exc.getBindingResult().getFieldErrors().stream()
                        .map(error -> new ValidationFailCause(error.getField(), error.getDefaultMessage()))
                        .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ValidationFailResponse(violations));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationFailResponse> handleConstraintViolationException(
            @NonNull final ConstraintViolationException exc
    ) {
        log.error(exc.getMessage());
        final List<ValidationFailCause> violations = exc.getConstraintViolations().stream()
                .map(
                        item -> new ValidationFailCause(
                                getFieldName(item.getPropertyPath().toString()),
                                item.getMessage()
                        )
                )
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ValidationFailResponse(violations));
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<ErrorResponse> handleConversionFailed(RuntimeException exc) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(createErrorMessage(exc)));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exc
    ) {
        log.error(exc.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(createErrorMessage(exc)));
    }

    private String createErrorMessage(@NonNull Exception exception) {
        return exception.getMessage();
    }

    private String getFieldName(String propertyPath) {
        String[] splitPath = propertyPath.split("\\.");
        return splitPath[splitPath.length - 1];
    }
}
