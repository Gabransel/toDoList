package com.gabransel.toDoList.dto;

import java.time.LocalDate;
import java.util.Objects;

public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate dateConclusion;

    public TaskResponseDTO(){
    }

    public TaskResponseDTO(Long id, String title, String description, LocalDate dateConclusion){
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateConclusion = dateConclusion;
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

    public LocalDate getDateConclusion (){
        return dateConclusion;
    }

    public void setDateConclusion(LocalDate dateConclusion){
        this.dateConclusion = dateConclusion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskResponseDTO taskDTO)) return false;
        return getTitle().equals(taskDTO.getTitle()) && getDescription().equals(taskDTO.getDescription()) && getDateConclusion().equals(taskDTO.getDateConclusion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getDateConclusion());
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateConclusion=" + dateConclusion +
                '}';
    }
}
