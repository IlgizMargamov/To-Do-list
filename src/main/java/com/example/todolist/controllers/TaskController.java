package com.example.todolist.controllers;

import com.example.todolist.models.SimpleTask;
import com.example.todolist.models.Task;
import com.example.todolist.repositories.TaskRepository;
import com.example.todolist.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    private final TaskServiceFactory m_taskServiceFactory;
    //private final AbstractTaskService abstractTaskService;

    //@Autowired // just spring annotation for its beans
    @Inject // universal for any objects in java (probably)
    public TaskController(/*@Qualifier("simpleTaskService")*/ /*AbstractTaskService abstractTaskService*/
    TaskServiceFactory taskServiceFactory) {
        m_taskServiceFactory=taskServiceFactory;
        //this.abstractTaskService = abstractTaskService;
    }

    @GetMapping(value = "/tasks")
    @ResponseBody // returns json representation
    public List<Task> getTasks(){
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        return abstractTaskService.getTasks();
    }

    @GetMapping(value = "/save/{name}")
    @ResponseBody
    public Task saveTask(@PathVariable String name){
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        Task task = new SimpleTask(name);
        abstractTaskService.saveTask(task);
        return task;
    }

    @GetMapping(value = "/save")
    @ResponseBody
    public Task saveTask(@RequestBody Task task){
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        abstractTaskService.saveTask(task);
        return task;
    }
}
