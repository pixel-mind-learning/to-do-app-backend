package com.coveragex.todo.dto.task;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author maleeshasa
 * @Date 2025-07-04
 */
@Getter
@Setter
public class TaskResponseDTO {
    private Integer id;
    private String title;
    private String description;
    private Boolean completed;
    private LocalDate dueDate;
}
