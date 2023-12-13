package ru.task.taskmanagementsystem.exception;

public class TaskServiceException extends RuntimeException {

    public TaskServiceException() {
    }

    public TaskServiceException(String message) {
        super(message);
    }
}
