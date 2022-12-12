package com.example.todolist.controllers;

import com.example.todolist.models.Category;
import com.example.todolist.repositories.CategoryRepository;
import com.example.todolist.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@Controller
public class CategoryController {
    private final CategoryService categoryService;
    @Inject
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value ="/tasks")
    public String createCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addAttribute("id",category.id);
        return "redirect:/tasks/categoryId={id}";
    }

}
