package com.gabransel.toDoList.v2.dto;

import com.gabransel.toDoList.v2.entities.TaskV2;
import com.gabransel.toDoList.v2.utils.ModelMapperUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskV2RequestDto {

    private String title;
    private String description;

    public TaskV2 taskV2RequestDtoToTaskV2(){
        return ModelMapperUtil.map(this, TaskV2.class);
    }
}
