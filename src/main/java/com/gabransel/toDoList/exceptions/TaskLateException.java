package com.gabransel.toDoList.exceptions;

import java.time.LocalDate;

public class TaskLateException extends RuntimeException {
    public TaskLateException(String title, LocalDate dateConclusion) {
        super("A tarefa '" + title + "' está atrasada! A data de conclusão era " + dateConclusion +
                ". Por favor, atualize a data ou marque como concluída.");
    }
}