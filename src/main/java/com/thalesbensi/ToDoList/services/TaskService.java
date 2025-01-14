package com.thalesbensi.ToDoList.services;

import com.thalesbensi.ToDoList.dtos.TaskDTO;
import com.thalesbensi.ToDoList.entities.Task;
import com.thalesbensi.ToDoList.enums.TaskStatus;
import com.thalesbensi.ToDoList.exceptions.IDNotFoundException;
import com.thalesbensi.ToDoList.repositories.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Transactional()
    public List<TaskDTO> findAll(Integer page, Integer items ) {
        Page<Task> dto = taskRepository.findAll(PageRequest.of(page, items));
        return dto.stream().map(x -> new TaskDTO(x)).toList();
    }

    @Transactional
    public TaskDTO findById(Long id) {
        Task entity = taskRepository.findById(id)
                .orElseThrow(() -> new IDNotFoundException(id));
        return new TaskDTO(entity);
    }

    @Transactional()
    public TaskDTO createTask(@Valid Task data) {
            Task entity = taskRepository.save(data);

            return new TaskDTO(data);
    }

    @Transactional
    public TaskDTO updateTask(@Valid Task data, Long id) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IDNotFoundException(id));


        existingTask.setTitle(data.getTitle());
        existingTask.setDescription(data.getDescription());
        existingTask.setUpdatedAt(new Date());

        Task updatedTask = taskRepository.save(existingTask);

        return new TaskDTO(updatedTask);
    }


    @Transactional
    public TaskDTO completeTask(Long id) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IDNotFoundException(id));

        existingTask.setTaskStatus(TaskStatus.COMPLETED);

        Task updatedTask = taskRepository.save(existingTask);

        return new TaskDTO(updatedTask);
    }

    	
   @Transactional
   public void deleteTask(Long id){

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IDNotFoundException(id));

        taskRepository.delete(task);

   }
}





