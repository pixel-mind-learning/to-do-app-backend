package com.coveragex.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author maleeshasa
 * @Date 2025-07-04
 */
@Getter
@Setter
@Builder
public class CommonResponse {
    private String message;
    private Object data;
    private HttpStatus status;
}
