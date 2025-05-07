package com.ecommercetong.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class BusinessScenarioNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 2L;

    public BusinessScenarioNotFoundException() {
        super();
    }

    public BusinessScenarioNotFoundException(String message) {
        super(message);
    }

    public BusinessScenarioNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessScenarioNotFoundException(Throwable cause) {
        super(cause);
    }

    protected BusinessScenarioNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
