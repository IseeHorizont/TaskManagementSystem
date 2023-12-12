package ru.task.taskmanagementsystem.entity;

public enum TaskStatus {

    WAIT("Wait"),
    PROCESSING("Processing"),
    DONE("Done");

    private String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
