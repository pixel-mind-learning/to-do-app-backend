package com.coveragex.todo.unitTest.controller;

import com.coveragex.todo.controller.TaskController;
import com.coveragex.todo.dto.CommonResponse;
import com.coveragex.todo.dto.task.TaskRequestDTO;
import com.coveragex.todo.dto.task.TaskResponseDTO;
import com.coveragex.todo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Test
    void testCreateTask() {
        TaskRequestDTO requestDTO = new TaskRequestDTO();
        requestDTO.setTitle("Take Home Assignment");
        requestDTO.setDescription("Finish the mid term assignment");
        CommonResponse mockResponse = CommonResponse.builder()
                .message("Task is created successfully.")
                .status(HttpStatus.OK)
                .data(new TaskResponseDTO())
                .build();

        when(taskService.createTask(requestDTO)).thenReturn(mockResponse);

        ResponseEntity<CommonResponse> response = taskController.createTask(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Task is created successfully.", response.getBody().getMessage());
        verify(taskService).createTask(requestDTO);
    }

    @Test
    void testGetAllPendingTodos() {
        CommonResponse mockResponse = CommonResponse.builder()
                .message("All pending todos fetched successfully.")
                .status(HttpStatus.OK)
                .data(List.of())
                .build();

        when(taskService.getAllPendingTodos()).thenReturn(mockResponse);

        ResponseEntity<CommonResponse> response = taskController.getAllPendingTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(taskService).getAllPendingTodos();
    }

    @Test
    void testMakeToDoCompleted() {
        int taskId = 1;
        CommonResponse mockResponse = CommonResponse.builder()
                .message("Task Completed.")
                .status(HttpStatus.OK)
                .build();

        when(taskService.makeToDoCompleted(taskId)).thenReturn(mockResponse);

        ResponseEntity<CommonResponse> response = taskController.makeToDoCompleted(taskId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(taskService).makeToDoCompleted(taskId);
    }
}
