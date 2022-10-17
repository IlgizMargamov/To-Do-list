package com.example.todolist.repositories;

import com.example.todolist.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);
}
