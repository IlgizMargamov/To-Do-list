package com.example.todolist.services;

import com.example.todolist.config.Helpers;
import com.example.todolist.models.SimpleTask;
import com.example.todolist.models.Task;
import com.example.todolist.repositories.TaskRepository;
import org.springframework.stereotype.Component;
import com.example.todolist.config.Helpers.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
//@Primary // sets as default service for injection
public class SimpleTaskService extends AbstractTaskService {

    private final TaskRepository m_taskRepository;

    @Inject
    public SimpleTaskService(TaskRepository m_taskRepository) {
        super(new ArrayList<>());
        this.m_taskRepository = m_taskRepository;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        m_taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Task getTaskById(Long id) {
        return m_taskRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    @Override
    public Task getTaskByUsername(String username) {
        return m_taskRepository.findAbstractTaskByUsername(username);
    }

    @Override
    public List<Task> getTasksByUsername(String username) {
        return m_taskRepository.findSimpleTasksByUsername(username);
    }

    @Override
    public Task saveTask(Task task) {
        task.setUsername(Helpers.getCurrentUser().orElseThrow(IllegalStateException::new));
        m_taskRepository.save((SimpleTask) task);
        return task;
    }
    public List<Task> getSimpleTasksByNameLikeAndUsername(String taskName, String username) {
        return m_taskRepository.getSimpleTasksByNameLikeAndUsername(taskName, username);
    }

    @Override
    public void deleteTask(Long id) {
        m_taskRepository.findById(id).ifPresent(m_taskRepository::delete);
    }

    @Override
    public List<Task> getTasksByCategoryId(Long id) {
        return m_taskRepository.findSimpleTasksByCategoryId(id);
    }

    // для полной инициализации bean,
    // иначе что-то может не успеть
    @PostConstruct
    private void init() {
        //m_tasks.add(new SimpleTask("b", "asd",0l));
    }

}
