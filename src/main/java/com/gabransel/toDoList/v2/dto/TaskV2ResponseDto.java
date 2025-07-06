package com.gabransel.toDoList.v2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gabransel.toDoList.v2.entities.TaskV2;
import com.gabransel.toDoList.v2.utils.ModelMapperUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskV2ResponseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate createdAt;
    private LocalDate done;

    public static TaskV2ResponseDto taskV2ToTaskV2ResponseDto(TaskV2 task){
        return ModelMapperUtil.map(task, TaskV2ResponseDto.class);
    }

    public static TaskV2 taskV2ResponseDtoToTaskV2(TaskV2ResponseDto taskV2){
        return ModelMapperUtil.map(taskV2, TaskV2.class);
    }
}
