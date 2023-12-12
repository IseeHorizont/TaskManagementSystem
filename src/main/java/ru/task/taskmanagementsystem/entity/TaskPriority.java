package ru.task.taskmanagementsystem.entity;

public enum TaskPriority {

    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private String priority;

    TaskPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }
}
