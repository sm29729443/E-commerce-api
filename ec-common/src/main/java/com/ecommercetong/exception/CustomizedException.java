package com.ecommercetong.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Data
public class CustomizedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private HttpStatus httpStatus;
    private String message;
    public CustomizedException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CustomizedException{" +
                "httpStatus=" + httpStatus +
                ", message='" + message + '\'' +
                '}';
    }
}
