package com.example.todolist.repositories;

import com.example.todolist.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {
    Category getCategoryById(Long id);
}
