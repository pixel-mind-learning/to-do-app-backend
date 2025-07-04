package com.coveragex.todo.mapper;

import com.coveragex.todo.dto.task.TaskRequestDTO;
import com.coveragex.todo.dto.task.TaskResponseDTO;
import com.coveragex.todo.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author maleeshasa
 * @Date 2025-07-04
 */
@Slf4j
@Component
public class TaskMapper {

    public Task mapToEntity(Task task, TaskRequestDTO taskRequest) {
        log.info("TaskMapper.mapToEntity() => started.");
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(Boolean.FALSE);
        log.info("TaskMapper.mapToEntity() => completed.");
        return task;
    }

    public TaskResponseDTO mapToDTO(TaskResponseDTO dto, Task task) {
        log.info("TaskMapper.mapToDTO() => started.");
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.getCompleted());
        log.info("TaskMapper.mapToDTO() => completed.");
        return dto;
    }

    public List<TaskResponseDTO> mapToDTOList(List<Task> tasks) {
        log.info("TaskMapper.mapToDTOList() => started.");
        return tasks.stream()
                .map(task -> mapToDTO(new TaskResponseDTO(), task))
                .toList();
    }
}
