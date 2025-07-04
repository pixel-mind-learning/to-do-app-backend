package com.coveragex.todo.dto.task;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author maleeshasa
 * @Date 2025-07-04
 */
@Getter
@Setter
public class TaskRequestDTO {
    @NotBlank(message = "Title is mandatory")
    private String title;

    private String description;
}
