package com.example.toDoApp.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateTaskRequest {

    private String title;
    private String context;
    private String dueDate;
    private String userId;
}
