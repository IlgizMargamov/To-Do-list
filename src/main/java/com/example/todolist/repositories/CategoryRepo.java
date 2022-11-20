package com.example.todolist.repositories;

import com.example.todolist.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {

}
