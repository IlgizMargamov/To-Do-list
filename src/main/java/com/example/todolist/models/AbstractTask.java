package com.example.todolist.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractTask implements Task{
/*
    private @Id @Setter(AccessLevel.PROTECTED) Long id;
*/
    @Id
    @GeneratedValue
    protected Long Id;

    protected String m_name;

    @Override
    public String getM_name() {
        return m_name;
    }

    @Override
    public Long getId() {
        return Id;
    }
}
