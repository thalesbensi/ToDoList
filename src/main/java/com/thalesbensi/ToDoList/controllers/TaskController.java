package com.thalesbensi.ToDoList.controllers;

import com.thalesbensi.ToDoList.dtos.TaskDTO;
import com.thalesbensi.ToDoList.entities.Task;
import com.thalesbensi.ToDoList.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping()
    public List<TaskDTO> findAll(){
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskDTO findById(@PathVariable Long id ){
        return taskService.findById(id);
    }

    @PostMapping()
    public TaskDTO createTask(@RequestBody @Valid Task data){
        return taskService.createTask(data);
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@RequestBody @Valid Task data, @PathVariable Long id){
    	return taskService.updateTask(data, id);
    }
    
    @PutMapping("/done/{id}")
    public TaskDTO completeTask(@PathVariable Long id){
    	return taskService.completeTask(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id ){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }


}
