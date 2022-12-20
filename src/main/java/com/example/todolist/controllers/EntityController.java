package com.example.todolist.controllers;

import com.example.todolist.config.Helpers;
import com.example.todolist.models.Category;
import com.example.todolist.models.SimpleTask;
import com.example.todolist.services.AbstractTaskService;
import com.example.todolist.services.CategoryService;
import com.example.todolist.services.TaskServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@Controller
public class EntityController {
    private final TaskServiceFactory m_taskServiceFactory;
    private final CategoryService categoryService;

    @Inject // universal for any objects in java (probably)
    public EntityController(TaskServiceFactory taskServiceFactory, CategoryService categoryService) {
        m_taskServiceFactory = taskServiceFactory;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/tasks/categoryId={id}")
    public String getMainPage(@PathVariable Long id, Model model) {
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        // model.addAttribute("taskName",taskName);
        var currentUsername = Helpers.getCurrentUser().orElseThrow(IllegalStateException::new);
        java.util.List<com.example.todolist.models.Task> tasks;
        tasks = id == 0 ? abstractTaskService.getTasksByUsername(currentUsername) : abstractTaskService.getTasksByCategoryId(id);

        model.addAttribute("tasks", tasks);
        //var tasks = id == 0 ? abstractTaskService.getTasksByUsername(currentUsername) : abstractTaskService.getTasksByCategoryId(id);
        var categories = categoryService.getCategoryByUsername(currentUsername);
        model.addAttribute("category", new Category()); //TODO: категория дважды создается при каждом обращение к tasks
        model.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping(value = "/tasks/search")
    public String getPageWithSearch(Model model, String taskName) {
        if ("".equals(taskName))
            return "redirect:/tasks/categoryId=0";
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        var currentUsername = Helpers.getCurrentUser().orElseThrow(IllegalStateException::new);
        var tasks = abstractTaskService.getSimpleTasksByNameLikeAndUsername(taskName, currentUsername);
        model.addAttribute("tasks", tasks);
        //var tasks = id == 0 ? abstractTaskService.getTasksByUsername(currentUsername) : abstractTaskService.getTasksByCategoryId(id);
        var categories = categoryService.getCategoryByUsername(currentUsername);
        model.addAttribute("category", new Category()); //TODO: категория дважды создается при каждом обращение к tasks
        model.addAttribute("categories", categories);
        return "index";
    }

    @PostMapping(value = "/tasks/categoryId={id}")
    public String getMainPage(@PathVariable Long id, Model model, String taskName) {
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        // model.addAttribute("taskName",taskName);
        var currentUsername = Helpers.getCurrentUser().orElseThrow(IllegalStateException::new);
        java.util.List<com.example.todolist.models.Task> tasks;
        if (taskName != null) {
            tasks = id == 0 ? abstractTaskService.getSimpleTasksByNameLikeAndUsername("курсач", currentUsername) : abstractTaskService.getTasksByCategoryId(id);
        } else {
            tasks = id == 0 ? abstractTaskService.getTasksByUsername(currentUsername) : abstractTaskService.getTasksByCategoryId(id);
        }
        model.addAttribute("tasks", tasks);
        //var tasks = id == 0 ? abstractTaskService.getTasksByUsername(currentUsername) : abstractTaskService.getTasksByCategoryId(id);
        var categories = categoryService.getCategoryByUsername(currentUsername);
        model.addAttribute("category", new Category()); //TODO: категория дважды создается при каждом обращение к tasks
        model.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping(value = "/task/create")
    public String createTask(Model model) {
        var categories = categoryService.getCategoryByUsername(Helpers.getCurrentUser().orElseThrow(IllegalStateException::new));
        model.addAttribute("task", new SimpleTask());
        model.addAttribute("button", "Создать задачу");
        model.addAttribute("categories", categories);
        return "create_task";
    }

    @PostMapping(value = "/task/create")
    public String createTask(@ModelAttribute("task") SimpleTask task, RedirectAttributes redirectAttributes) {
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        abstractTaskService.saveTask(task);
        redirectAttributes.addAttribute("id", task.getCategoryId());
        return "redirect:/tasks/categoryId={id}";
    }


    @GetMapping(value = "/task/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        var task = m_taskServiceFactory.getService().getTaskById(id);
        var categories = categoryService.getCategoryByUsername(Helpers.getCurrentUser().orElseThrow(IllegalStateException::new));
        model.addAttribute("task", task);
        model.addAttribute("button", "Изменить задачу");
        model.addAttribute("categories", categories);
        return "create_task";
    }

    @PostMapping(value = "/task/edit/{id}")
    public String editTask(@ModelAttribute("task") SimpleTask task, RedirectAttributes redirectAttributes) {
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        abstractTaskService.saveTask(task);
        redirectAttributes.addAttribute("id", task.getCategoryId());
        return "redirect:/tasks/categoryId={id}";
    }
}
