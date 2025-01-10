package com.thalesbensi.ToDoList;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication()
@OpenAPIDefinition(info = @Info(title = "ToDoList RESTful API", version = "1", description = "API developed as part of a study project to reinforce Back-end development concepts using Spring Boot."))
public class ToDoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}

}
