package com.gabransel.toDoList.service;

import com.gabransel.toDoList.dto.TaskDTO;
import com.gabransel.toDoList.entities.Task;
import com.gabransel.toDoList.exceptions.TaskDuplicateException;
import com.gabransel.toDoList.exceptions.TaskLateException;
import com.gabransel.toDoList.exceptions.TaskNotFoundException;
import com.gabransel.toDoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

        if (taskRepository.existsByTitleIgnoreCaseAndDataConclusion(taskDTO.getTitle(), taskDTO.getDateConclusion())){
            throw new TaskDuplicateException(
                    " Já existe tarefa com o título '" + taskDTO.getTitle() +
                            "' para a data " + taskDTO.getDateConclusion());
        }

        Task task = new Task ();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDateConclusion(taskDTO.getDateConclusion());
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(String title, LocalDate dateConclusion, TaskDTO dto) {
        Task task = searchTask(title, dateConclusion);

        if(!task.getTitle().equals(dto.getTitle()) || !task.getDateCriation().equals(dto.getDateConclusion())) {

            if (taskRepository.existsByTitleIgnoreCaseAndDataConclusion(dto.getTitle(), dto.getDateConclusion())) {
                throw new TaskDuplicateException(
                        "Já existe uma tarefa com o novo título e data informados.");
            }
        }

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(String title, LocalDate dateConclusion){
        Task task = searchTask(title, dateConclusion);
        taskRepository.delete(task);
    }

    @Transactional(readOnly = true)
    public List<Task> listAllTasks(){
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Task searchTask(String title, LocalDate dateConclusion) {
       return taskRepository
               .findByTitleIgnoreCaseAndDataConclusion(title,dateConclusion)
               .orElseThrow(() ->new TaskNotFoundException(
                       "Tarefa com título ' " + title +
                               "' e data" + dateConclusion +" não encontrada"));
    }

    @Transactional(readOnly = true)
    public String taskStatus(String title, LocalDate dateConclusion) {
        Task task = searchTask(title, dateConclusion);

        if (task.isDone()) {
            return "A tarefa '" +task.getTitle() + "' já foi concluída.";
        }

        if(LocalDate.now().isAfter(task.getDateConclusion())){
            throw new TaskLateException(task.getTitle(), task.getDateConclusion());
        }

        return "A tarefa '" +task.getTitle() +"' estáem dia! Conckuir até: " + task.getDateConclusion();
    }




}
