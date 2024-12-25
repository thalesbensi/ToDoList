package com.thalesbensi.ToDoList.controllers;

import com.thalesbensi.ToDoList.dtos.TaskDTO;
import com.thalesbensi.ToDoList.entities.Task;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping()
    public List<TaskDTO> findAll(){
        return taskService.findAll:
    }

    @GetMapping("/{id}")
    public TaskDTO findById(@PathVariable Long id ){
        return taskService.findById(id);
    }

    @PostMapping("/{id}")
    public TaskDTO findById(@RequestBody @Valid Task data){
        return taskService.createTask(data);
    }

    @PutMapping("/{id}")
    public TaskDTO findById(@RequestBody @Valid Task data){
        return taskService.updateTask(data);
    }

    @DeleteMapping("/{id}")
    public TaskDTO findById(@PathVariable Long id ){
        return taskService.deleteTask(id);
    }


}
