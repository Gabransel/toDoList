package com.gabransel.toDoList.controller;

import com.gabransel.toDoList.dto.TaskDTO;
import com.gabransel.toDoList.entities.Task;
import com.gabransel.toDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    public ResponseEntity<Task> create(@RequestBody TaskDTO dto) {
        Task taskCreated = taskService.createTask(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskCreated);
    }

    @GetMapping
    public ResponseEntity<Task> search(
            @RequestParam String title,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {

        Task task = taskService.searchTask(title, date);
        return ResponseEntity.ok(task);
    }

    @PutMapping
    public ResponseEntity<Task> update(
            @RequestParam String title,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam TaskDTO dto) {

        Task taskUpdated = taskService.updateTask(title, date, dto);
        return ResponseEntity.ok(taskUpdated);
    }

    @DeleteMapping
    public ResponseEntity<Task> delete(
            @RequestParam String title,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        taskService.deleteTask(title, date);
        return ResponseEntity.noContent().build();
    }



}
