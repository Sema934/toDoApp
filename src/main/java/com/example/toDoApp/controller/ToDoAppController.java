package com.example.toDoApp.controller;

import com.example.toDoApp.domain.Task;
import com.example.toDoApp.dto.TaskDTO;
import com.example.toDoApp.request.CreateTaskRequest;
import com.example.toDoApp.request.UpdateTaskRequest;
import com.example.toDoApp.request.UserRequest;
import com.example.toDoApp.service.ToDoAppCommandService;
import com.example.toDoApp.service.ToDoAppQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class ToDoAppController {

    private final ToDoAppQueryService toDoAppQueryService;
    private final ToDoAppCommandService toDoAppCommandService;

    @Autowired
    public ToDoAppController(ToDoAppQueryService toDoAppQueryService,
                             ToDoAppCommandService toDoAppCommandService) {
        this.toDoAppQueryService = toDoAppQueryService;
        this.toDoAppCommandService = toDoAppCommandService;
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@RequestBody UserRequest request) {
        toDoAppCommandService.createUser(request);
    }

    @PostMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void createTask(@RequestBody CreateTaskRequest request) {
        toDoAppCommandService.createTask(request);
    }

    @PutMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateTask(@RequestBody @Valid UpdateTaskRequest request) {
        toDoAppCommandService.updateTask(request);
    }

    @DeleteMapping(value = "/task/{TASK_ID}", consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@NotNull @PathVariable("TASK_ID") String taskId) {
        toDoAppCommandService.deleteTask(taskId);
    }

    @GetMapping(value = "/task/{TASK_ID}", consumes = MediaType.ALL_VALUE,
            produces = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO getTask(@NotNull @PathVariable("TASK_ID") String taskId) {
        return toDoAppQueryService.getTask(taskId);
    }

    @GetMapping(value = "/taskByUserId/{USER_ID}", consumes = MediaType.ALL_VALUE)
    @ResponseStatus( HttpStatus.OK)
    public List<TaskDTO> getTasksByUserId (@NotNull @PathVariable("USER_ID") String userId) {
        return toDoAppQueryService.getTasksByUserId(userId);
    }
}
