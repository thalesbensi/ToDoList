package com.thalesbensi.ToDoList.exceptions;

public class IDNotFoundException extends RuntimeException {

    public IDNotFoundException(Long id){
        super("Task with ID "+ id + " not found!");
    }

    public IDNotFoundException(String message){
        super(message);
    }

}
