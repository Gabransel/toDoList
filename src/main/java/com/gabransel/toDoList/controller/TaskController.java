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

    //Não precisa de autowired quando se faz a injeção de dependência dessa forma (via constructor)
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO dto) {
        TaskDTO taskCreated = taskService.createTask(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskCreated);
    }

    @GetMapping
    public ResponseEntity<TaskDTO> search(
            @RequestParam String title,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {

        TaskDTO task = taskService.searchTask(title, date);
        return ResponseEntity.ok(task);
    }

    @PutMapping
    public ResponseEntity<TaskDTO> update(
            @RequestParam String title,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam TaskDTO dto) {

        TaskDTO taskUpdated = taskService.updateTask(title, date, dto);
        return ResponseEntity.ok(taskUpdated);
    }

    //Aqui você pode usar Void invés de passar o "taskDto".
    @DeleteMapping
    public ResponseEntity<Void> delete(
            @RequestParam String title,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        taskService.deleteTask(title, date);
        return ResponseEntity.noContent().build();
    }



}
