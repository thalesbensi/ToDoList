package com.thalesbensi.ToDoList.exceptions.exceptionHandlers;

import com.thalesbensi.ToDoList.exceptions.IDNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IDNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleIDNotFoundException(IDNotFoundException exception) {

        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }
}

