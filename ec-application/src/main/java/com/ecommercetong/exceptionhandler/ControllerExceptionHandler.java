package com.ecommercetong.exceptionhandler;

import com.ecommercetong.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.ecommercetong.controller")
public class ControllerExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse
                .wrapperResponse(HttpStatus.BAD_REQUEST,e.getFieldError().getDefaultMessage(), null));
    }
}
