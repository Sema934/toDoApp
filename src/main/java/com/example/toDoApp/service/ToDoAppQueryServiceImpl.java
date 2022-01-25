package com.example.toDoApp.service;

import com.example.toDoApp.domain.Task;
import com.example.toDoApp.domain.User;
import com.example.toDoApp.dto.TaskDTO;
import com.example.toDoApp.repository.TaskRepository;
import com.example.toDoApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ToDoAppQueryServiceImpl implements ToDoAppQueryService{

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ToDoAppQueryServiceImpl (UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDTO getTask(String taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            return task.get().toDTO();
        }

        return null;
    }

    @Override
    public List<TaskDTO> getTasksByUserId(String userId) {
        Optional<User> user = userRepository.findById(userId);
        List<Task> todos = taskRepository.findByUser_Id(user.get().getId());

        if (!CollectionUtils.isEmpty(todos)) {
            return todos.stream().map(todo -> todo.toDTO()).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
