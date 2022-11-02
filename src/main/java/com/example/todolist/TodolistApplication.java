package com.example.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// for building and starting in local vm
// mvn package -and java -jar target/todolist-0.0.1-snapshot.jar

//
// docker run -p 8080:8080 todolist

@SpringBootApplication
@EntityScan // ищет сущности в пакетах местных
@EnableJpaRepositories // ищет репозитории в пакетах
public class TodolistApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }
}
