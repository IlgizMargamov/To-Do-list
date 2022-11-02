package com.example.todolist.services;

import com.example.todolist.models.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

//@Component // базовая аннотация
//@Service // маркирующий
//@Controller // маркирующий
public abstract class AbstractTaskService implements TaskService {

    protected List<Task> m_tasks;

    public AbstractTaskService() {

    }

    public AbstractTaskService(List<Task> tasks) {
        m_tasks = tasks;
    }


    @Override
    public List<Task> getTasks() {
        return m_tasks;
    }

    @Override
    public Task getTaskById(Long id) {
        return m_tasks
                .stream()
                .filter(x -> Objects.equals(x.getId(), id))
                .toList()
                .stream()
                .findFirst()
                .get();
    }

    @Override
    public Task getTaskByUsername(String username) {
        return m_tasks
                .stream()
                .filter(x -> Objects.equals(x.getUsername(), username))
                .toList()
                .stream()
                .findFirst()
                .get();
    }

    public List<Task> getTasksByUsername(String username) {
        return m_tasks
                .stream()
                .filter(x -> Objects.equals(x.getUsername(), username))
                .toList();
    }

    public Task saveTask(Task task) {
        return task;
    }
}
