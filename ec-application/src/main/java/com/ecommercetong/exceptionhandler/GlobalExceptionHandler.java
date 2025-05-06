package com.ecommercetong.exceptionhandler;

import com.ecommercetong.exception.CustomizedException;
import com.ecommercetong.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleException(CustomizedException e) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse
                .wrapperResponse(e.getHttpStatus(),e.getMessage(), null));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse
                .wrapperResponse(HttpStatus.BAD_REQUEST,e.getFieldError().getDefaultMessage(), null));
    }
}
