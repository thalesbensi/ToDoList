package com.thalesbensi.ToDoList.services;

import com.thalesbensi.ToDoList.dtos.TaskDTO;
import com.thalesbensi.ToDoList.entities.Task;
import com.thalesbensi.ToDoList.enums.TaskStatus;
import com.thalesbensi.ToDoList.repositories.TaskRepository;
import jdk.jfr.Timestamp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Date;
import java.util.List;


class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should Return Success while Returning Page List of Tasks")
    void findAllSuccess() {
       //Arrange: Setup Data and Mocks
        Task task = new Task(1L,"Eat Fruits","You must eat fruits", TaskStatus.PENDING, new Date(),new Date());
        List<Task> taskList = Collections.singletonList(task);
        Page<Task> taskPage = new PageImpl<>(taskList);
        Mockito.when(taskRepository.findAll(Mockito.any(Pageable.class))).thenReturn(taskPage);

        Integer page = 0;
        Integer size = 5;

        //Act: Run The Method That Should Be Tested
        List<TaskDTO> tasks = taskService.findAll(page, size);

        //Assert: Validate the results
        Assertions.assertNotNull(tasks, "The Page Should Not Be Null");
        Assertions.assertEquals(1, tasks.size(), "The List Size must be 1");
        Mockito.verify(taskRepository, Mockito.times(1)).findAll(Mockito.any(Pageable.class));
        Mockito.verifyNoMoreInteractions(taskRepository);

    }


    @Test
    void findById() {
    }

    @Test
    void createTask() {
    }

    @Test
    void updateTask() {
    }

    @Test
    void completeTask() {
    }

    @Test
    void deleteTask() {
    }
}