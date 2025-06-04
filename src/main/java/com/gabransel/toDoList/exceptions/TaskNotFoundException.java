package com.gabransel.toDoList.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Tarefa não encotrada");
    }
}
