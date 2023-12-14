package ru.task.taskmanagementsystem.entity;

public enum Role {

    USER("Пользователь");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
