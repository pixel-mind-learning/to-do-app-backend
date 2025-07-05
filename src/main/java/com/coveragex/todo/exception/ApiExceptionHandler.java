package com.coveragex.todo.exception;

import com.coveragex.todo.dto.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<CommonResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorList = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(
                fieldError -> errorList.add(fieldError.getDefaultMessage())
        );
        log.error("MethodArgumentNotValidException : {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK.value()).body(CommonResponse.builder().status(HttpStatus.BAD_REQUEST).message(errorList.get(0)).build());
    }
}
