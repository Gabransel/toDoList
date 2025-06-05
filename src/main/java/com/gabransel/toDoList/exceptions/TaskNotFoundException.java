package com.gabransel.toDoList.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException byId(Long id) {
        return new TaskNotFoundException("Nenhuma tarefa econtrada");
    }

    public static TaskNotFoundException byTitle(String titulo) {
        return new TaskNotFoundException("Nenhuma tarefa encontrada");
    }
}