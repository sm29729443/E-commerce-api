package com.ecommercetong.exceptionhandler;

import com.ecommercetong.exception.BusinessScenarioException;
import com.ecommercetong.exception.CustomizedException;
import com.ecommercetong.exception.InternalServerException;
import com.ecommercetong.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<?> handleException(MethodArgumentNotValidException e) {
    List<String> list =
        e.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .toList();
    log.error("MethodArgumentNotValidException: {}", e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ApiResponse.wrapperResponse(HttpStatus.BAD_REQUEST, "參數錯誤", list));
  }

  @ExceptionHandler
  public ResponseEntity<?> handleException(InternalServerException e) {
    log.error("InternalServerException: {}", e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            ApiResponse.wrapperResponse(
                HttpStatus.INTERNAL_SERVER_ERROR, "伺服器處理錯誤, 請聯絡客服人員:xxxx-xxx", null));
  }

  @ExceptionHandler
  public ResponseEntity<?> handleException(BusinessScenarioException e) {
    log.error("BusinessScenarioException: {}", e.getMessage());
    return ResponseEntity.status(e.getHttpStatus())
        .body(ApiResponse.wrapperResponse(e.getHttpStatus(), e.getMessage(), null));
  }

  @ExceptionHandler
  public ResponseEntity<?> handleException(Exception e) {
    log.error("Exception: {}", e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            ApiResponse.wrapperResponse(
                HttpStatus.INTERNAL_SERVER_ERROR, "非預期的錯誤, 請聯絡客服人員:xxxx-xxx", null));
  }
}
