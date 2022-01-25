package com.example.toDoApp.service;

import com.example.toDoApp.request.CreateTaskRequest;
import com.example.toDoApp.request.UpdateTaskRequest;
import com.example.toDoApp.request.UserRequest;

public interface ToDoAppCommandService {
    void createUser(UserRequest request);

    void createTask(CreateTaskRequest request);

    void updateTask(UpdateTaskRequest request);

    void deleteTask(String taskId);
}
