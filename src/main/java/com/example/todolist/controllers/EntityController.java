package com.example.todolist.controllers;

import com.example.todolist.config.Helpers;
import com.example.todolist.models.Category;
import com.example.todolist.models.SimpleTask;
import com.example.todolist.services.AbstractTaskService;
import com.example.todolist.services.CategoryService;
import com.example.todolist.services.TaskServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

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
    public String getMainPage(@PathVariable Long id, Model model, String taskName) {
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        model.addAttribute("taskName", taskName);
        var currentUsername = Helpers.getCurrentUser().orElseThrow(IllegalStateException::new);
        var tasks = id == 0 ? abstractTaskService.getTasksByUsername(currentUsername) : abstractTaskService.getTasksByCategoryId(id);
        var categories = categoryService.getCategoryByUsername(currentUsername);
        model.addAttribute("tasks", tasks);
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
    public String createTask(@ModelAttribute("task") SimpleTask task, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        abstractTaskService.saveTask(task);
        redirectAttributes.addAttribute("id", task.getCategoryId());
        String fileName = handleFile(task, file, redirectAttributes);
        if (fileName == null) return "redirect:/tasks/categoryId={id}";
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
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
    public String editTask(@ModelAttribute("task") SimpleTask task, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        var fileName=handleFile(task, file, redirectAttributes);
        if (fileName == null) return "redirect:/tasks/categoryId={id}";
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
        AbstractTaskService abstractTaskService = m_taskServiceFactory.getService();
        abstractTaskService.saveTask(task);
        redirectAttributes.addAttribute("id", task.getCategoryId());
        return "redirect:/tasks/categoryId={id}";
    }

    @GetMapping("/task/download/{id}")
    public void downloadFile(@PathVariable Long id, HttpServletResponse response) {
        try {
            var task = m_taskServiceFactory.getService().getTaskById(id);

            String fileName = Paths.get(Helpers.getUserFolderName() + task.getFileName()).toString();
            response.setContentType("application/*");
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", task.getFileName());
            response.setHeader(headerKey,  headerValue);
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(fileName);
                try {
                    int c;
                    while ((c = inputStream.read()) != -1) {
                        response.getWriter().write(c);
                    }
                } finally {
                    if (inputStream != null)
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    response.getWriter().close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String handleFile(SimpleTask task, MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return null;
        }

        var fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            var path = Paths.get(Helpers.getUserFolderName() + fileName);
            task.setFileName(fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileName;
    }
}
