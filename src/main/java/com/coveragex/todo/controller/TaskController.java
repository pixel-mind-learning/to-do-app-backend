package com.coveragex.todo.controller;

import com.coveragex.todo.dto.CommonResponse;
import com.coveragex.todo.dto.task.TaskRequestDTO;
import com.coveragex.todo.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author maleeshasa
 * @Date 2025-07-04
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/api/task")
public class TaskController {

    private final TaskService taskService;

    /**
     * This method is allowed to create a new task.
     *
     * @param taskRequest {@link TaskRequestDTO} - task request details
     * @return {@link ResponseEntity<CommonResponse>} - task created response
     * @author maleeshasa
     */
    @PostMapping(value = "/create")
    public ResponseEntity<CommonResponse> createTask(@RequestBody @Valid TaskRequestDTO taskRequest) {
        log.info("TaskController.createTask() => started.");
        return ResponseEntity.ok(taskService.createTask(taskRequest));
    }

    /**
     * This method is allowed to fetch all pending tasks.
     *
     * @return {@link ResponseEntity<CommonResponse>} - all pending tasks response
     * @author maleeshasa
     */
    @GetMapping(value = "/get-all-pending-todos")
    public ResponseEntity<CommonResponse> getAllPendingTodos() {
        log.info("TaskController.getAllPendingTodos() => started.");
        return ResponseEntity.ok(taskService.getAllPendingTodos());
    }

    /**
     * This method is allowed to mark a task as completed.
     *
     * @param id {@link Integer} - task id
     * @return {@link ResponseEntity<CommonResponse>} - task completed response
     * @author maleeshasa
     */
    @PostMapping(value = "/make-todo-completed")
    public ResponseEntity<CommonResponse> makeToDoCompleted(@RequestParam(value = "id") Integer id) {
        log.info("TaskController.makeToDoCompleted() => started.");
        return ResponseEntity.ok(taskService.makeToDoCompleted(id));
    }
}
