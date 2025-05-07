package com.ecommercetong.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends CustomizedException{
    public InternalServerException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}
