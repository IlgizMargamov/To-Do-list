package com.example.todolist.services;

import com.example.todolist.helpers.TaskMode;
import com.example.todolist.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class TaskServiceFactory {

    @Value("${tasksToRun}")
    private String tasksToRun;

    private final TaskRepository m_taskRepository;

    @Inject
    public TaskServiceFactory(TaskRepository taskRepository) {
        m_taskRepository = taskRepository;
    }

    public AbstractTaskService getService() {
        TaskMode taskMode = TaskMode.toTaskMode(this.tasksToRun);

        switch (taskMode) {
            case Simple -> {
                return new SimpleTaskService(m_taskRepository);
            }
            case SubTask -> {
                return new SimpleTaskWithSubtaskService();
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
