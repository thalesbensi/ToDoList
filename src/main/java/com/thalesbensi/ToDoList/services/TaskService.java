package com.thalesbensi.ToDoList.services;

import com.thalesbensi.ToDoList.dtos.TaskDTO;
import com.thalesbensi.ToDoList.entities.Task;
import com.thalesbensi.ToDoList.enums.TaskStatus;
import com.thalesbensi.ToDoList.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Transactional()
    public List<TaskDTO> findAll() {
        List<Task> dto = taskRepository.findAll();
        return dto.stream().map(x -> new TaskDTO(x)).toList();
    }

    @Transactional()
    public TaskDTO findById(Long id) {
        Task entity = taskRepository.findById(id).get();
        return new TaskDTO(entity);
    }

    @Transactional()
    public TaskDTO createTask(@Valid Task data) {
        taskRepository.save(data);
        return new TaskDTO(data);
    }

    @Transactional
    public TaskDTO updateTask(@Valid Task data, Long id){

        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()) {

            Task existingTask = task.get();
            existingTask.setTitle(data.getTitle());
            existingTask.setDescription(data.getDescription());
            existingTask.setUpdatedAt(new Date());

            Task savedTask = taskRepository.save(existingTask);
            return new TaskDTO(savedTask);
            
        } else {
            throw new EntityNotFoundException("Task with ID " + id + " not found");
        }
    }
    
    @Transactional
    public TaskDTO completeTask(Long id) {
    	
    	Optional<Task> task = taskRepository.findById(id);
    	
    	if (task.isPresent()) {

            Task existingTask = task.get();
            existingTask.setTaskStatus(TaskStatus.COMPLETED);
            
            Task savedTask = taskRepository.save(existingTask);
            return new TaskDTO(savedTask);

        } else {
            throw new EntityNotFoundException("Task with ID " + id + " not found");
        }
    }
    	
   @Transactional
   public void deleteTask(Long id){

        Optional<Task> task = taskRepository.findById(id);

        if(task.isPresent()){
            taskRepository.deleteById(id);

        } else {
            throw new EntityNotFoundException("Task with ID " + id + " not found");
        }
   }
}





