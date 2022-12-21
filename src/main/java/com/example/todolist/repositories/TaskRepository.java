package com.example.todolist.repositories;

import com.example.todolist.models.SimpleTask;
import com.example.todolist.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public interface TaskRepository extends CrudRepository<SimpleTask, Long> {
    /*boolean existsBym_name(String m_name);*/
    SimpleTask findAbstractTaskByUsername(String username);

    List<Task> findSimpleTasksByUsername(String username);
    List<Task> findSimpleTasksByCategoryId(Long categoryId);
    List<Task> getSimpleTasksByNameLikeAndUsername(String taskName, String username);

}
