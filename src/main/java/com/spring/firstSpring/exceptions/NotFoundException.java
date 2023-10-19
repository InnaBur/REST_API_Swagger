package com.spring.firstSpring.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
