package com.thalesbensi.ToDoList.exceptions.exceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class RestErrorMessage {

    private HttpStatus httpStatus;
    private String message;

}
