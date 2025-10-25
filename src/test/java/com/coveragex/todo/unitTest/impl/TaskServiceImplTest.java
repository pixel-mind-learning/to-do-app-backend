package com.coveragex.todo.unitTest.impl;

import com.coveragex.todo.dto.CommonResponse;
import com.coveragex.todo.dto.task.TaskRequestDTO;
import com.coveragex.todo.dto.task.TaskResponseDTO;
import com.coveragex.todo.mapper.TaskMapper;
import com.coveragex.todo.model.Task;
import com.coveragex.todo.repository.TaskRespository;
import com.coveragex.todo.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRespository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @Test
    void testCreateTaskSuccess() {
        TaskRequestDTO requestDTO = new TaskRequestDTO();
        requestDTO.setTitle("Take Home Assignment");
        requestDTO.setDescription("Finish the mid term assignment");
        Task task = new Task();
        Task savedTask = new Task();
        TaskResponseDTO responseDTO = new TaskResponseDTO();

        when(taskMapper.mapToEntity(any(), eq(requestDTO))).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(savedTask);
        when(taskMapper.mapToDTO(any(), eq(savedTask))).thenReturn(responseDTO);

        CommonResponse response = taskService.createTask(requestDTO);

        assertEquals("Task is created successfully.", response.getMessage());
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNotNull(response.getData());
    }

    @Test
    void testMakeToDoCompleted() {
        Task task = new Task();
        task.setCompleted(false);

        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        CommonResponse response = taskService.makeToDoCompleted(1);

        assertEquals("Task Completed.", response.getMessage());
        assertEquals(HttpStatus.OK, response.getStatus());
        verify(taskRepository).save(task);
    }

    @Test
    void testGetAllPendingTodos() {
        List<Task> tasks = List.of(new Task());
        when(taskRepository.findByCompletedFalseOrderByIdAsc(PageRequest.of(0, 5))).thenReturn(tasks);
        when(taskMapper.mapToDTOList(tasks)).thenReturn(List.of(new TaskResponseDTO()));

        CommonResponse response = taskService.getAllPendingTodos();

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("All pending todos fetched successfully.", response.getMessage());
        assertNotNull(response.getData());
    }
}

