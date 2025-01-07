package com.thalesbensi.ToDoList.repositories;

import com.thalesbensi.ToDoList.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, String> {

    public UserDetails findByLogin(String login);
}
