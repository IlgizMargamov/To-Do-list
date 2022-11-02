package com.example.todolist.services;

import com.example.todolist.models.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasks();

    Task getTaskById(Long id);

    Task getTaskByUsername(String username);

    Task saveTask(Task task);
}
