package com.example.todolist.services;

import com.example.todolist.models.Task;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

//@Component // базовая аннотация
//@Service // маркирующий
//@Controller // маркирующий
public abstract class AbstractTaskService implements TaskService{

    protected List<Task> m_tasks;

    public AbstractTaskService(){

    }

    public AbstractTaskService(List<Task> tasks){
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

    public Task saveTask(Task task) {
return task;
    }
}
