package com.gabransel.toDoList.repository;

import com.gabransel.toDoList.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitleIgnoreCaseAndDataConclusion(String title, LocalDate dateConclusion);

    boolean existsByTitleIgnoreCaseAndDataConclusion(String title, LocalDate dataConclusion);

    List<Task> findByTitleContainingIgnoreCase(String title);

}


