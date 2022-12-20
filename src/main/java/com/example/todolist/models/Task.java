package com.example.todolist.models;

import lombok.Getter;

import java.io.Serializable;

public interface Task extends Serializable {
    String getName();

    Long getId();

    String getUsername();

    void setUsername(String username);

    String getDescription();

    Long getCategoryId();

    void setCategoryId(Long categoryId);
}
