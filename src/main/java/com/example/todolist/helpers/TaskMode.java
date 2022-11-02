package com.example.todolist.helpers;

public enum TaskMode {
    Simple("simple"),
    SubTask("subTask");

    private final String m_name;

    TaskMode(String name) {
        m_name = name;
    }

    public String toString() {
        return m_name;
    }

    public static TaskMode toTaskMode(String name) {
        return switch (name) {
            case "simple" -> Simple;
            case "subTask" -> SubTask;
            default -> throw new IllegalArgumentException("Argument {name} is in illegal state " + name);
        };
    }
}
