package com.gabransel.toDoList.mappers;

import com.gabransel.toDoList.dto.TaskDTO;
import com.gabransel.toDoList.dto.TaskResponseDTO;
import com.gabransel.toDoList.entities.Task;

public class TaskMapper {

    public static TaskResponseDTO taskToTaskDto(Task task){
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDateConclusion()
        );
    }

    public static Task taskDtoToTask(TaskResponseDTO taskDTO){
        //Copiei o que você fez lá no service e joguei pra cá
        Task task = new Task ();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDateConclusion(taskDTO.getDateConclusion());
        return task;
    }

    public static Task taskRequestDtoToTask(TaskDTO taskDTO){
        //Copiei o que você fez lá no service e joguei pra cá
        Task task = new Task ();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDateConclusion(taskDTO.getDateConclusion());
        return task;
    }
}
