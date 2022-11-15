package com.example.todolist.controllers;

import com.example.todolist.models.Task;
import com.example.todolist.models.User;
import com.example.todolist.services.AbstractTaskService;
import com.example.todolist.services.TaskService;
import com.example.todolist.services.TaskServiceFactory;
import com.example.todolist.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

@Controller
public class RegistrationController {
    private final UserService m_userService;
    private final TaskServiceFactory m_taskServiceFactory;

    @Inject
    public RegistrationController(UserService userService, TaskServiceFactory taskServiceFactory) {
        m_userService = userService;
        m_taskServiceFactory = taskServiceFactory;
    }

    @GetMapping("/getUser")
    @ResponseBody
    public List<Task> getUser() {
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        return abstractTaskService.getTasks();
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/tasks";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        try {
            m_userService.addUser(user);
            return "redirect:/login";
        } catch (Exception ex) {
            model.addAttribute("message", "User already exists");
            return "registration";
        }
    }

    
}

