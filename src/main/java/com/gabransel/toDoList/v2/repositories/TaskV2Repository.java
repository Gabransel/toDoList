package com.gabransel.toDoList.v2.repositories;

import com.gabransel.toDoList.v2.entities.TaskV2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskV2Repository extends JpaRepository<TaskV2, Long> {

    List<TaskV2> findAllByCreatedAtBetween(LocalDate startDate, LocalDate endDate);
    Optional<TaskV2> findByTitle(String title);
}
