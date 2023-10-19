package com.spring.firstSpring.exceptions;

import java.time.LocalDateTime;

public class MyErrorMessage {

    private LocalDateTime data;
    private String message;
    private String details;

    public MyErrorMessage(LocalDateTime data, String message, String details) {
        this.data = data;
        this.message = message;
        this.details = details;
    }

}
