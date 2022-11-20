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
    public int TaskId=0;

    public SimpleTask(String name, String description, int taskId) {
        m_name = name;
        description = description;
        TaskId=taskId;
    }
}
