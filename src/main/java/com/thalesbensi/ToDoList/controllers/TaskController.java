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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks", produces ={"application/json"})
@Tag(name = "Endpoints For Task Operations")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Operation(summary = "Return a Page Tasks in Database based on RequestParams Page and Size", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK / List Returned"),
            @ApiResponse(responseCode = "401", description = "Unauthorized / User Not Authenticated"),
            @ApiResponse(responseCode = "403", description = "Forbidden / User Don't Have The Necessary Role To Perform This Action")
    })
    @GetMapping()
    public ResponseEntity<List<TaskDTO>> findAll(@RequestParam int page,
                                                 @RequestParam int size){

        return ResponseEntity.ok(taskService.findAll(page, size)) ;
    }


    @Operation(summary = "Find a Task by Id in Database", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK / Task Returned Returned"),
            @ApiResponse(responseCode = "404", description = "Not Found / Task with this Id not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized / User Not Authenticated"),
            @ApiResponse(responseCode = "403", description = "Forbidden / User Don't Have The Necessary Role To Perform This Action")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> findById(@PathVariable Long id ){

        return ResponseEntity.ok(taskService.findById(id));
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
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid Task data){
        TaskDTO createdTask = taskService.createTask(data);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }


    @Operation(summary = "Update a Task by Id in Database using a Id as a parameter", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK / Task Updated"),
            @ApiResponse(responseCode = "404", description = "Not Found / Task with this Id not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized / User Not Authenticated"),
            @ApiResponse(responseCode = "403", description = "Forbidden / User Don't Have The Necessary Role To Perform This Action")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody @Valid Task data, @PathVariable Long id){
    	return ResponseEntity.ok(taskService.updateTask(data, id));

    }


    @Operation(summary = "Set a Task status as COMPLETED in Database using a Id as a parameter", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK / Task Updated"),
            @ApiResponse(responseCode = "404", description = "Not Found / Task with this Id not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized / User Not Authenticated"),
            @ApiResponse(responseCode = "403", description = "Forbidden / User Don't Have The Necessary Role To Perform This Action")
    })
    @PutMapping("/done/{id}")
    public ResponseEntity<TaskDTO> completeTask(@PathVariable Long id){
    	return ResponseEntity.ok(taskService.completeTask(id));
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
