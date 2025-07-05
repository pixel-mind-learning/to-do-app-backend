package com.coveragex.todo.service.impl;

import com.coveragex.todo.dto.CommonResponse;
import com.coveragex.todo.dto.task.TaskRequestDTO;
import com.coveragex.todo.dto.task.TaskResponseDTO;
import com.coveragex.todo.exception.RecordNotFoundException;
import com.coveragex.todo.mapper.TaskMapper;
import com.coveragex.todo.model.Task;
import com.coveragex.todo.repository.TaskRespository;
import com.coveragex.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author maleeshasa
 * @Date 2025-07-04
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRespository taskRespository;

    /**
     * This method is allowed to create a new task.
     *
     * @param taskRequest {@link TaskRequestDTO} - task request details
     * @return {@link CommonResponse} - task created response
     * @author maleeshasa
     */
    @Override
    @Transactional
    public CommonResponse createTask(TaskRequestDTO taskRequest) {
        log.info("TaskServiceImpl.createTask() => started.");
        try {
            Task savedEntity = taskRespository.save(taskMapper.mapToEntity(new Task(), taskRequest));
            return CommonResponse.builder()
                    .message("Task is created successfully.")
                    .data(taskMapper.mapToDTO(new TaskResponseDTO(), savedEntity))
                    .status(HttpStatus.OK)
                    .build();

        } catch (Exception e) {
            log.error("Error occurred while creating task: {}", e.getMessage());
            return CommonResponse.builder()
                    .message("Task creation failed.")
                    .status(HttpStatus.BAD_REQUEST)
                    .data(null)
                    .build();
        }
    }

    /**
     * This method is allowed to mark a task as completed.
     *
     * @param id {@link Integer} - task id
     * @return {@link CommonResponse} - task completed response
     * @author maleeshasa
     */
    @Override
    public CommonResponse makeToDoCompleted(Integer id) {
        log.info("TaskServiceImpl.makeToDoCompleted() => started.");
        Task task = taskRespository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Task not found."));

        task.setCompleted(Boolean.TRUE);
        taskRespository.save(task);
        return CommonResponse.builder()
                .message("Task Completed.")
                .status(HttpStatus.OK)
                .data(null)
                .build();
    }

    /**
     * This method is allowed to fetch all pending tasks.
     *
     * @return {@link CommonResponse} - all pending tasks response
     * @author maleeshasa
     */
    @Override
    public CommonResponse getAllPendingTodos() {
        log.info("TaskServiceImpl.getAllPendingTodos() => started.");
        List<Task> tasks = taskRespository.findByCompletedFalseOrderByIdAsc();
        if (!tasks.isEmpty()) {
            return CommonResponse.builder()
                    .message("All pending todos fetched successfully.")
                    .status(HttpStatus.OK)
                    .data(taskMapper.mapToDTOList(tasks))
                    .build();
        } else {
            return CommonResponse.builder()
                    .message("No pending todos found.")
                    .status(HttpStatus.NOT_FOUND)
                    .data(null)
                    .build();
        }
    }
}
