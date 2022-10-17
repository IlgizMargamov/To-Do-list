package com.example.todolist.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isActive;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name= "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>();

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }
}
