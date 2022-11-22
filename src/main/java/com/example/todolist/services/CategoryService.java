package com.example.todolist.services;

import com.example.todolist.config.Helpers;
import com.example.todolist.models.Category;
import com.example.todolist.repositories.CategoryRepository;
import com.google.inject.Inject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Inject
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    public List<Category> getCategoryByUsername(String username) {
        return categoryRepository.getCategoriesByUsername(username);
    }

    public Category getCategoryById(Long id)
    {
        return categoryRepository.findById(id).get();
    }

    public Category save(Category category) {
        category.setUsername(Helpers.getCurrentUser());
        categoryRepository.save(category);
        return category;
    }

}
