package com.example.todolist.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SimpleTask extends AbstractTask {

    public SimpleTask(String name, String description, Long taskId) {
        m_name = name;
        this.description = description;
        this.categoryId =taskId;
    }
}
