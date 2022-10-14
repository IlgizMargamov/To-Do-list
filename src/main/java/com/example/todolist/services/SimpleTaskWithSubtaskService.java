package com.example.todolist.services;

import com.example.todolist.models.SimpleTask;
import com.example.todolist.models.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleTaskWithSubtaskService extends AbstractTaskService {

    public SimpleTaskWithSubtaskService(List<Task> tasks) {
        super(tasks);
        tasks.add(new SimpleTask("c"));
        tasks.add(new SimpleTask("d"));
    }

    public SimpleTaskWithSubtaskService() {
        super(new ArrayList<>());
    }

    @Override
    public Task saveTask(Task task) {
return task;
    }
}
