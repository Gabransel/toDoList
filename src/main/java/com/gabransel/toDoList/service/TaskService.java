package com.gabransel.toDoList.service;

import com.gabransel.toDoList.dto.TaskDTO;
import com.gabransel.toDoList.dto.TaskResponseDTO;
import com.gabransel.toDoList.entities.Task;
import com.gabransel.toDoList.exceptions.TaskDuplicateException;
import com.gabransel.toDoList.exceptions.TaskLateException;
import com.gabransel.toDoList.exceptions.TaskNotFoundException;
import com.gabransel.toDoList.mappers.TaskMapper;
import com.gabransel.toDoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.gabransel.toDoList.mappers.TaskMapper.*;

//TODO: melhore o inglês das classes e a clareza do que você está codando. Por exemplo, "criation" está incorreto, o correto é "Creation". Use o linguee para te auxiliar.
//TODO: Quando eu digo a clareza é mudar de "taskStatus" para "getTaskStatus" ou "ReceiveTaskStatus".
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskResponseDTO createTask(TaskDTO taskDTO) {

        if (taskRepository.existsByTitleIgnoreCaseAndDateConclusion(taskDTO.getTitle(), taskDTO.getDateConclusion())){
            throw new TaskDuplicateException(
                    " Já existe tarefa com o título '" + taskDTO.getTitle() +
                            "' para a data " + taskDTO.getDateConclusion());
        }
        Task task = taskRequestDtoToTask(taskDTO);
        //Aqui eu retornei o nosso mapper passando o objeto Task que já havia sido gerado tbm por um outro mapper
        //Você estava retornado para o controller o seu 'entity'. Você deveria retornar um DTO. Resumidamente... você recebe um DTO do cliente e devolve um DTO
        return taskToTaskDto(
                taskRepository.save(task)
        );
    }

    @Transactional
    public TaskResponseDTO updateTask(String title, LocalDate dateConclusion, TaskDTO dto) {
        //Devido a forma que você construiu a classe task e a taskdto eu precisei voltar ele para entity para depois transforma-lo em um DTO novamente após passar pelo banco.
        Task task = taskDtoToTask(searchTask(title, dateConclusion));
        /**
         * Aqui eu converti o teu LocalDateTime para LocalDate. Pq?
         * 1 - Você usou o equals para comparar as datas e o compilador jogou um warning pra você pois não é o ideal
         * 2 - Você tem um LocalDateTime que recebe uma data e um horário do dia e tentou comparar Data e hora com uma Data (LocalDate).
         * 3 - Então eu trouxe o seu LocalDateTime para LocalDate para fazer sentido na conversão.
         */
        if(!task.getTitle().equals(dto.getTitle()) || !task.getDateCriation().toLocalDate().isEqual(dto.getDateConclusion())) {

            if (taskRepository.existsByTitleIgnoreCaseAndDateConclusion(dto.getTitle(), dto.getDateConclusion())) {
                throw new TaskDuplicateException(
                        "Já existe uma tarefa com o novo título e data informados.");
            }
        }

        return taskToTaskDto(taskRepository.save(task));
    }

    @Transactional
    public void deleteTask(String title, LocalDate dateConclusion){
        TaskResponseDTO task = searchTask(title, dateConclusion);
        taskRepository.deleteById(task.getId());
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> listAllTasks(){
        return taskRepository.findAll()
                .stream()
                .map(t -> taskToTaskDto(t)) //Aqui eu usei uma forma simplificada mas abaixo vou deixar um comentário usando Method Reference
                .toList();

        //Com Method Reference
        //.map(TaskMapper::taskToTaskDto)
        //.toList();
    }

    @Transactional(readOnly = true)
    public TaskResponseDTO searchTask(String title, LocalDate dateConclusion) {
       return taskRepository
               .findByTitleIgnoreCaseAndDateConclusion(title,dateConclusion)
               .map(TaskMapper::taskToTaskDto) //usei method reference
               .orElseThrow(() ->new TaskNotFoundException(
                       "Tarefa com título ' " + title +
                               "' e data" + dateConclusion +" não encontrada"));
    }

    @Transactional(readOnly = true)
    public String taskStatus(String title, LocalDate dateConclusion) {
        TaskResponseDTO task = searchTask(title, dateConclusion);

        if (taskDtoToTask(task).isDone()) {
            //Aqui você poderia retornar um DTO que carrega essa mensagem e não somente o texto.
            return "A tarefa '" +task.getTitle() + "' já foi concluída.";
        }

        if(LocalDate.now().isAfter(task.getDateConclusion())){
            throw new TaskLateException(task.getTitle(), task.getDateConclusion());
        }

        return "A tarefa '" +task.getTitle() +"' estáem dia! Conckuir até: " + task.getDateConclusion();
    }




}
