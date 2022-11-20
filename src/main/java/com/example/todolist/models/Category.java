package com.example.todolist.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@Getter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    public long Id;
    @Setter
    public String Name;

}
