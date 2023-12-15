package ru.task.taskmanagementsystem.util;

import org.springframework.core.convert.converter.Converter;
import ru.task.taskmanagementsystem.constant.TaskStatus;

public class StringToEnumConverter implements Converter<String, TaskStatus> {

    @Override
    public TaskStatus convert(String source) {
        return TaskStatus.valueOf(source.toUpperCase());
    }
}
