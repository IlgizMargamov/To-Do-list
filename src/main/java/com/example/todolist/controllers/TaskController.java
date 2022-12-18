package com.example.todolist.controllers;

import com.example.todolist.models.Category;
import com.example.todolist.models.SimpleTask;
import com.example.todolist.models.Task;
import com.example.todolist.repositories.TaskRepository;
import com.example.todolist.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.todolist.config.Helpers;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @GetMapping(value ="/task/delete/{id}")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes)
    {
        var categoryId = m_taskServiceFactory.getService().getTaskById(id).getCategoryId();
        redirectAttributes.addAttribute("categoryId", categoryId);
        m_taskServiceFactory.getService().deleteTask(id);
        return "redirect:/tasks/categoryId={categoryId}";
    }


    @GetMapping(value = "/save")
    @ResponseBody
    public Task saveTask(@RequestBody Task task) {
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        abstractTaskService.saveTask(task);
        return task;
    }
}
