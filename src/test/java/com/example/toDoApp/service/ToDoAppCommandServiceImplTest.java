package com.example.toDoApp.service;

import com.example.toDoApp.domain.Task;
import com.example.toDoApp.domain.User;
import com.example.toDoApp.repository.TaskRepository;
import com.example.toDoApp.repository.UserRepository;
import com.example.toDoApp.request.CreateTaskRequest;
import com.example.toDoApp.request.UpdateTaskRequest;
import com.example.toDoApp.request.UserRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class ToDoAppCommandServiceImplTest {

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRequest userRequest;
    @Mock
    private User user;

    @Mock
    private CreateTaskRequest createTaskRequest;
    @Mock
    private Task task;
    @Mock
    private UpdateTaskRequest updateTaskRequest;

    private ToDoAppCommandServiceImpl service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        this.service = new ToDoAppCommandServiceImpl(bCryptPasswordEncoder, userRepository,
                taskRepository);
        this.service = new ToDoAppCommandServiceImpl(bCryptPasswordEncoder, userRepository,
                taskRepository);
    }

    @Test
    public void createUser() {
        final String password = "";
        Mockito.when(bCryptPasswordEncoder.encode(userRequest.getPassword())).thenReturn(password);
        user.setPassword(password);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        userRequest.setPassword(password);
        service.createUser(userRequest);;
    }

    @Test
    public void createTask() {
        Mockito.when(userRepository.findById(createTaskRequest.getUserId())).thenReturn(Optional.of(user));
        task.setUser(user);
        Mockito.when(taskRepository.save(task)).thenReturn(task);
        service.createTask(createTaskRequest);
    }

    @Test
    public void updateTask() {
        Mockito.when(taskRepository.findById(updateTaskRequest.getId())).thenReturn(Optional.of(task));
        Mockito.when(taskRepository.save(task)).thenReturn(task);
        service.updateTask(updateTaskRequest);

    }

    @Test
    public void deleteTask() {
        String taskId = "1L";
        Mockito.when(taskRepository.findById(updateTaskRequest.getId())).thenReturn(Optional.of(task));
        Mockito.doNothing().when(taskRepository).delete(task);
        service.deleteTask(taskId);
    }
}