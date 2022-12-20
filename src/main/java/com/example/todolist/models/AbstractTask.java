package com.example.todolist.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class AbstractTask implements Task {
    /*
        private @Id @Setter(AccessLevel.PROTECTED) Long id;
    */
    @Id
    @GeneratedValue
    protected Long Id;

    protected String m_name;

    protected String username;

    protected String description;
    protected Long categoryId =0l;
    protected String fileName;
}
