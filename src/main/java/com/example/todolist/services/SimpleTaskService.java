package com.example.todolist.services;

import com.example.todolist.models.SimpleTask;
import com.example.todolist.models.Task;
import com.example.todolist.repositories.TaskRepository;
import org.springframework.stereotype.Component;

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
    public List<Task> getTasks(){
        List<Task> tasks = new ArrayList<>();
        m_taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Task getTaskById(Long id){
        return m_taskRepository.findById(id).get();
    }

    @Override
    public Task saveTask(Task task) {
        m_taskRepository.save((SimpleTask) task);
        return task;
    }

    // для полной инициализации bean,
    // иначе что-то может не успеть
    @PostConstruct
    private void init() {
        m_tasks.add(new SimpleTask("b"));
    }

}
