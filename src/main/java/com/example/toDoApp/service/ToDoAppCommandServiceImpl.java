package com.example.toDoApp.service;

import com.example.toDoApp.domain.Task;
import com.example.toDoApp.domain.User;
import com.example.toDoApp.repository.TaskRepository;
import com.example.toDoApp.repository.UserRepository;
import com.example.toDoApp.request.CreateTaskRequest;
import com.example.toDoApp.request.UpdateTaskRequest;
import com.example.toDoApp.request.UserRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class ToDoAppCommandServiceImpl implements ToDoAppCommandService{

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ToDoAppCommandServiceImpl (BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository,
                                      TaskRepository taskRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void createUser(UserRequest request) {

        User user = new User();
        if (StringUtils.isNotEmpty(request.getPassword())) {
            final String encryptedPassword = bCryptPasswordEncoder.encode(request.getPassword());
            user.setPassword(encryptedPassword);
        }
        user.setUserName(request.getUserName());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());

        userRepository.save(user);
    }

    @Override
    public void createTask(CreateTaskRequest request) {

        Optional<User> user = userRepository.findById(request.getUserId());
        Task task = new Task();

        if (user.isPresent()) {
            task.setUser(user.get());
        }

        task.setTitle(request.getTitle());
        task.setContext(request.getContext());
        task.setStatus("ACTIVE");
        //task.setDueDate(request.getDueDate());

        taskRepository.save(task);
    }

    @Override
    public void updateTask(UpdateTaskRequest request) {
        Optional<Task> task = taskRepository.findById(request.getId());

        if (task.isPresent()) {
            Task todo = task.get();

            if (!StringUtils.isEmpty(request.getTitle())) {
                todo.setTitle(request.getTitle());
            }
            if (!StringUtils.isEmpty(request.getContext())) {
                todo.setContext(request.getContext());
            }
            if (!StringUtils.isEmpty(request.getStatus())) {
                todo.setStatus(request.getStatus());
            }
            if (request.getUserId() != null) {
                User oldUser = todo.getUser();
                Optional<User> user = userRepository.findById(request.getUserId());
                if (user.isPresent()) {
                    todo.setUser(user.get());
                    user.get().setTodos(new ArrayList<Task>(Arrays.asList(todo)));

                    oldUser.setTodos(new ArrayList<>());
                    userRepository.save(oldUser);
                    userRepository.save(user.get());
                }
            }
            if (request.getDueDate() != null) {
                todo.setDueDate(request.getDueDate());
            }

            taskRepository.save(todo);
        }

    }

    @Override
    public void deleteTask(String taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            taskRepository.delete(task.get());
        }
    }
}
