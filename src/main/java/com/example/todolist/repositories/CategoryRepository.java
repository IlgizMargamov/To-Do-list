package com.example.todolist.repositories;

import com.example.todolist.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findCategoryById(Long Id);
    List<Category> getCategoriesByUsername(String username);
}
