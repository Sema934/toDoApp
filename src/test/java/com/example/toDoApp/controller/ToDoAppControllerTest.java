package com.example.toDoApp.controller;

import com.example.toDoApp.dto.TaskDTO;
import com.example.toDoApp.dto.UserDTO;
import com.example.toDoApp.request.CreateTaskRequest;
import com.example.toDoApp.request.UpdateTaskRequest;
import com.example.toDoApp.request.UserRequest;
import com.example.toDoApp.service.ToDoAppCommandService;
import com.example.toDoApp.service.ToDoAppQueryService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

public class ToDoAppControllerTest extends Assertions {

    private ToDoAppController controller;

    @Mock
    private ToDoAppQueryService toDoAppQueryService;

    @Mock
    private ToDoAppCommandService toDoAppCommandService;

    @Mock
    UserRequest userRequest;
    @Mock
    CreateTaskRequest createTaskRequest;
    @Mock
    UpdateTaskRequest updateTaskRequest;
    @Mock
    TaskDTO taskDTO;
    @Mock
    UserDTO userDTO;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        this.controller = new ToDoAppController(toDoAppQueryService, toDoAppCommandService);
    }

    @Test
    public void createUser() {
        Mockito.doNothing().when(toDoAppCommandService).createUser(userRequest);
        controller.createUser(userRequest);
    }

    @Test
    public void createTask() {
        Mockito.doNothing().when(toDoAppCommandService).createTask(createTaskRequest);
        controller.createTask(createTaskRequest);
    }

    @Test
    public void updateTaskStatus() {
        Mockito.doNothing().when(toDoAppCommandService).updateTask(updateTaskRequest);
        controller.updateTask(updateTaskRequest);
    }

    @Test
    public void deleteTask() throws Exception {
        Mockito.doNothing().when(toDoAppCommandService).deleteTask("1L");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task/{TASK_ID}", "1L"));
    }

    @Test
    public void getTasksByUserId() throws Exception {
        List<TaskDTO> taskList = new ArrayList<>();
        taskList.add(taskDTO);

        Mockito.when(toDoAppQueryService.getTasksByUserId("1L")).thenReturn(taskList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/task/{USER_ID}", "1L"));
    }
}