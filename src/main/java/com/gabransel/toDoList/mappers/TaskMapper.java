package com.gabransel.toDoList.mappers;

import com.gabransel.toDoList.dto.TaskDTO;
import com.gabransel.toDoList.entities.Task;

public class TaskMapper {

    public static TaskDTO taskToTaskDto(Task task){
        return new TaskDTO(
                task.getTitle(),
                task.getDescription(),
                task.getDateConclusion()
        );
    }

    public static Task taskDtoToTask(TaskDTO taskDTO){
        //Copiei o que você fez lá no service e joguei pra cá
        Task task = new Task ();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDateConclusion(taskDTO.getDateConclusion());
        return task;
    }
}
