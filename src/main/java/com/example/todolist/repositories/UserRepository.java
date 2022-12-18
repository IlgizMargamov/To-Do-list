package com.example.todolist.repositories;

import com.example.todolist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);

    User findByGoogleUsername(String googleUsername);
}
