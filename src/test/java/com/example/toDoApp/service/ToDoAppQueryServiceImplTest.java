package com.example.toDoApp.service;

import com.example.toDoApp.domain.User;
import com.example.toDoApp.repository.TaskRepository;
import com.example.toDoApp.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;

public class ToDoAppQueryServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private User user;

    private ToDoAppQueryServiceImpl service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        this.service = new ToDoAppQueryServiceImpl(userRepository, taskRepository);
    }

    @Test
    public void getTasksByUserId() {
        String userId = "1L";
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        service.getTasksByUserId(userId);
    }
}