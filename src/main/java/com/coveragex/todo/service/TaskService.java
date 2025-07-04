package com.coveragex.todo.service;

import com.coveragex.todo.dto.CommonResponse;
import com.coveragex.todo.dto.task.TaskRequestDTO;

/**
 * @author maleeshasa
 * @Date 2025-07-04
 */
public interface TaskService {

    /**
     * This method is allowed to create a new task.
     *
     * @param taskRequest {@link TaskRequestDTO} - task request details
     * @return {@link CommonResponse} - task created response
     * @author maleeshasa
     */
    CommonResponse createTask(TaskRequestDTO taskRequest);

    /**
     * This method is allowed to mark a task as completed.
     *
     * @param id {@link Integer} - task id
     * @return {@link CommonResponse} - task completed response
     * @author maleeshasa
     */
    CommonResponse makeToDoCompleted(Integer id);

    /**
     * This method is allowed to fetch all pending tasks.
     *
     * @return {@link CommonResponse} - all pending tasks response
     * @author maleeshasa
     */
    CommonResponse getAllPendingTodos();
}
