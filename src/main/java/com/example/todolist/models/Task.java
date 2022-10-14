package com.example.todolist.models;

import java.io.Serializable;

public interface Task extends Serializable {
    String getM_name();
    Long getId();
}
