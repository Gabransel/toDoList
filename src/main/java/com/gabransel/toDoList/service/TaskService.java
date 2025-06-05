package com.gabransel.toDoList.service;

import com.gabransel.toDoList.dto.TaskDTO;
import com.gabransel.toDoList.entities.Task;
import com.gabransel.toDoList.exceptions.TaskNotFoundException;
import com.gabransel.toDoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task ();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDateConclusion(taskDTO.getDateConclusion());
        return taskRepository.save(task);
    }


    @Transactional(readOnly = true)
    public List<Task> listAllTasks(){
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Task> searchTaskByTitle(String title) {
        List<Task> task = taskRepository.findByTitleContainingIgnoreCase(title);
        if (title.isEmpty()) {
            throw TaskNotFoundException.byTitle(title);
        }
        return task;
    }




}
