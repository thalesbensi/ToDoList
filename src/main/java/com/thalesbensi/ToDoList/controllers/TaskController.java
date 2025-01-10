package com.thalesbensi.ToDoList.controllers;

import com.thalesbensi.ToDoList.dtos.TaskDTO;
import com.thalesbensi.ToDoList.entities.Task;
import com.thalesbensi.ToDoList.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks", produces ={"application/json"})
@Tag(name = "ToDoList RESTful API")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Operation(summary = "Return a List of All Tasks in Database", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK / List Returned"),
            @ApiResponse(responseCode = "401", description = "Unauthorized / User Not Authenticated"),
            @ApiResponse(responseCode = "403", description = "Forbidden / User Don't Have The Necessary Role To Perform This Action")
    })
    @GetMapping()
    public List<TaskDTO> findAll(){
        return taskService.findAll();
    }



    @Operation(summary = "Find a Task by Id in Database", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK / Task Returned Returned"),
            @ApiResponse(responseCode = "404", description = "Not Found / Task with this Id not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized / User Not Authenticated"),
            @ApiResponse(responseCode = "403", description = "Forbidden / User Don't Have The Necessary Role To Perform This Action")
    })
    @GetMapping("/{id}")
    public TaskDTO findById(@PathVariable Long id ){

        return taskService.findById(id);
    }


    @Operation(summary = "Create a Task in Database", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created / Task Created"),
            @ApiResponse(responseCode = "404", description = "Not Found / Task with this Id not found"),
            @ApiResponse(responseCode = "400", description = "Bad Request / Invalid Parameters While Creating a Task"),
            @ApiResponse(responseCode = "401", description = "Unauthorized / User Not Authenticated"),
            @ApiResponse(responseCode = "403", description = "Forbidden / User Don't Have The Necessary Role To Perform This Action")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDTO createTask(@RequestBody @Valid Task data){
        return taskService.createTask(data);
    }


    @Operation(summary = "Update a Task by Id in Database using a Id as a parameter", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK / TaskUpdated"),
            @ApiResponse(responseCode = "404", description = "Not Found / Task with this Id not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized / User Not Authenticated"),
            @ApiResponse(responseCode = "403", description = "Forbidden / User Don't Have The Necessary Role To Perform This Action")
    })
    @PutMapping("/{id}")
    public TaskDTO updateTask(@RequestBody @Valid Task data, @PathVariable Long id){
    	return taskService.updateTask(data, id);
    }


    @Operation(summary = "Set a Task status as COMPLETED in Database using a Id as a parameter", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK / TaskUpdated"),
            @ApiResponse(responseCode = "404", description = "Not Found / Task with this Id not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized / User Not Authenticated"),
            @ApiResponse(responseCode = "403", description = "Forbidden / User Don't Have The Necessary Role To Perform This Action")
    })
    @PutMapping("/done/{id}")
    public TaskDTO completeTask(@PathVariable Long id){
    	return taskService.completeTask(id);
    }


    @Operation(summary = "Delete a Task in Database using the Id as a parameter", method = "DELETE")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content / Task Deleted"),
            @ApiResponse(responseCode = "404", description = "Not Found / Task with this Id not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized / User Not Authenticated"),
            @ApiResponse(responseCode = "403", description = "Forbidden / User Don't Have The Necessary Role To Perform This Action")
    })
    public ResponseEntity deleteTask(@PathVariable Long id ){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
