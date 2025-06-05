package com.gabransel.toDoList.exceptions;

public class TaskDuplicateException extends RuntimeException {

    public TaskDuplicateException(String message){
        super(message);
    }
}
