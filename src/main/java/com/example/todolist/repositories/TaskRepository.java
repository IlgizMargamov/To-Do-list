package com.example.todolist.repositories;

import com.example.todolist.models.SimpleTask;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<SimpleTask, Long> {
    /*boolean existsBym_name(String m_name);*/
}
