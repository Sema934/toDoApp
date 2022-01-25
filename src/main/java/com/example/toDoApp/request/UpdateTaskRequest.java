package com.example.toDoApp.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UpdateTaskRequest {

    @NotNull
    private String id;
    private String title;
    private String context;
    private String dueDate;
    private String userId;
    private String status;

}
