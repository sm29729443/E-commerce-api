package com.ecommercetong.exception;

import org.springframework.http.HttpStatus;

public class BusinessScenarioException extends CustomizedException {
  public BusinessScenarioException(HttpStatus httpStatus, String message) {
    super(httpStatus, message);
  }

}
