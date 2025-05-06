package com.ecommercetong.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Function;

@Data
public class ApiResponse<T> {

  private Integer status;
  private String message;
  private T data;

  public ApiResponse() {}

  private static <T> ApiResponse<T> build(HttpStatus status, String message, T data) {
    ApiResponse<T> apiResponse = new ApiResponse<>();
    apiResponse.setStatus(status.value());
    apiResponse.setMessage(message);
    apiResponse.setData(data);
    return apiResponse;
  }

  private static <T> ApiResponse<T> build(HttpStatus status, T data) {
    return build(status, status.getReasonPhrase(), data);
  }

  public static <T> ApiResponse<T> wrapperResponse(HttpStatus status, String message, T data) {
    return Optional.ofNullable(message)
        .map(msg -> build(status, msg, data))
        .orElseGet(() -> build(status, data));
  }
}
