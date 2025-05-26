package com.gabransel.toDoList.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate dateConclusion;
    private LocalDateTime dateCriation;
    private boolean done = false;

    public Task() {
    }

    public Task(Long id, String title,String description, LocalDate dateConclusion, LocalDateTime dateCriation){
       this.id = id;
       this.title = title;
       this.description = description;
       this.dateConclusion = dateConclusion;
       this.dateCriation = dateCriation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateConclusion() {
        return dateConclusion;
    }

    public void setDateConclusion(LocalDate dateConclusion) {
        this.dateConclusion = dateConclusion;
    }

    public LocalDateTime getDateCriation() {
        return dateCriation;
    }

    public void setDateCriation(LocalDateTime dateCriation) {
        this.dateCriation = dateCriation;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return isDone() == task.isDone() && getId().equals(task.getId()) && getTitle().equals(task.getTitle()) && getDescription().equals(task.getDescription()) && getDateConclusion().equals(task.getDateConclusion()) && getDateCriation().equals(task.getDateCriation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getDateConclusion(), getDateCriation(), isDone());
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", titulo='" + title + '\'' +
                ", descricao='" + description + '\'' +
                ", dataConclusao=" + dateConclusion +
                ", dataCriacao=" + dateConclusion +
                ", concluida=" + done +
                '}';
    }
}
