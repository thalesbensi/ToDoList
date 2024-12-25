package com.thalesbensi.ToDoList.dtos;

import com.thalesbensi.ToDoList.entities.Task;
import com.thalesbensi.ToDoList.enums.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private Date createdAt;
    private Date updatedAt;

    public TaskDTO(Task entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
