package com.gabransel.toDoList.v2.services;

import com.gabransel.toDoList.v2.dto.TaskV2RequestDto;
import com.gabransel.toDoList.v2.dto.TaskV2ResponseDto;
import com.gabransel.toDoList.v2.entities.TaskV2;
import com.gabransel.toDoList.v2.repositories.TaskV2Repository;
import com.gabransel.toDoList.v2.utils.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.gabransel.toDoList.v2.dto.TaskV2ResponseDto.taskV2ResponseDtoToTaskV2;
import static com.gabransel.toDoList.v2.dto.TaskV2ResponseDto.taskV2ToTaskV2ResponseDto;

@RequiredArgsConstructor
@Service
public class TaskV2Service {

    private final TaskV2Repository taskV2Repository;

    @Transactional(readOnly = true)
    public List<TaskV2ResponseDto> findAllByPeriod(LocalDate startDate, LocalDate endDate){
        return this.taskV2Repository.findAllByCreatedAtBetween(startDate, endDate)
                .stream()
                .map(TaskV2ResponseDto::taskV2ToTaskV2ResponseDto)
                .toList();
    }


    public TaskV2ResponseDto save(TaskV2RequestDto taskV2RequestDto){
        TaskV2 task = taskV2RequestDto.taskV2RequestDtoToTaskV2();
        task.setCreatedAt(LocalDate.now());
        return taskV2ToTaskV2ResponseDto(
                this.taskV2Repository.save(task)
        );
    }

    @Transactional(readOnly = true)
    public TaskV2ResponseDto findById(Long id){
        return this.taskV2Repository.findById(id)
                .map(TaskV2ResponseDto::taskV2ToTaskV2ResponseDto)
                .orElseThrow(() -> new IllegalArgumentException("Task not found, id: "+id));
    }

    @Transactional(readOnly = true)
    public TaskV2ResponseDto findByTitle(String title){
        return this.taskV2Repository.findByTitle(title)
                .map(TaskV2ResponseDto::taskV2ToTaskV2ResponseDto)
                .orElseThrow(() -> new IllegalArgumentException("Task with title '"+title+"' not found"));

    }

    public TaskV2ResponseDto update(Long id, TaskV2RequestDto neo){
        TaskV2ResponseDto taskDto = findById(id);
        TaskV2ResponseDto updated = ModelMapperUtil.partialUpdate(neo, taskDto);

        TaskV2 entity = taskV2ResponseDtoToTaskV2(updated);

        return taskV2ToTaskV2ResponseDto(
                this.taskV2Repository.save(entity)
        );
    }


    public void deleteById(Long id){
        TaskV2ResponseDto taskV2ResponseDto = findById(id);
        this.taskV2Repository.deleteById(taskV2ResponseDto.getId());
    }

}
