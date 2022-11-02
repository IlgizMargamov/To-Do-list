package com.example.todolist.controllers;

import com.example.todolist.models.SimpleTask;
import com.example.todolist.models.Task;
import com.example.todolist.repositories.TaskRepository;
import com.example.todolist.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.todolist.config.Helpers;

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
        m_taskServiceFactory = taskServiceFactory;
        //this.abstractTaskService = abstractTaskService;
    }

    @GetMapping(value = "/tasks")
    public String getTasks(Model model) {
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        var tasks = abstractTaskService.getTasksByUsername(Helpers.getCurrentUser());
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping(value = "/task/create")
    public String createTask(Model model) {
        model.addAttribute("task", new SimpleTask());
        return "create_task";
    }

    @PostMapping(value = "/task/create")
    public String createTask(@ModelAttribute("task") SimpleTask task) {
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        abstractTaskService.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping(value = "/save")
    @ResponseBody
    public Task saveTask(@RequestBody Task task) {
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        abstractTaskService.saveTask(task);
        return task;
    }
}
