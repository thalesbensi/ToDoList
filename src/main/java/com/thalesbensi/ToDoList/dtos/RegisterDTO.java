package com.thalesbensi.ToDoList.dtos;

import com.thalesbensi.ToDoList.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role ) {
}
