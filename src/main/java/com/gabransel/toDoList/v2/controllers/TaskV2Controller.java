package com.gabransel.toDoList.v2.controllers;

import com.gabransel.toDoList.v2.dto.TaskV2RequestDto;
import com.gabransel.toDoList.v2.dto.TaskV2ResponseDto;
import com.gabransel.toDoList.v2.services.TaskV2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v2/tasks")
public class TaskV2Controller {

    private final TaskV2Service taskV2Service;

    @GetMapping()
    public ResponseEntity<List<TaskV2ResponseDto>> findAllByPeriod(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
            ){
        return ResponseEntity.ok().body(this.taskV2Service.findAllByPeriod(startDate, endDate));
    }


    @PostMapping
    public ResponseEntity<TaskV2ResponseDto> save(@RequestBody TaskV2RequestDto taskV2RequestDto){
        return ResponseEntity
                .status(201)
                .body(this.taskV2Service.save(taskV2RequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskV2ResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.taskV2Service.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<TaskV2ResponseDto> findByTitle(
            @RequestParam String title
    ){
        return ResponseEntity.ok().body(
          this.taskV2Service.findByTitle(title)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<TaskV2ResponseDto> updated(@PathVariable Long id, @RequestBody TaskV2RequestDto taskV2RequestDto){
        return ResponseEntity.ok().body(
            this.taskV2Service.update(id, taskV2RequestDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        this.taskV2Service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
