package com.example.toDoApp.service;

import com.example.toDoApp.domain.Task;
import com.example.toDoApp.dto.TaskDTO;

import java.util.List;

public interface ToDoAppQueryService {
    TaskDTO getTask(String taskId);
    List<TaskDTO> getTasksByUserId(String userId);
}
