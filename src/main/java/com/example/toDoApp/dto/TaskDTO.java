package com.example.toDoApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDTO {
    private String id;

    private String title;
    private String context;
    private String dueDate;
    private String status;
    private UserDTO user;
}
