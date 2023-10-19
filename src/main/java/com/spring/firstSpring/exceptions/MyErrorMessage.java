package com.spring.firstSpring.exceptions;

import java.time.LocalDateTime;

public class MyErrorMessage {

    private LocalDateTime data;
    private int status;
    private String details;

    public MyErrorMessage(LocalDateTime data, int status, String details) {
        this.data = data;
        this.status = status;
        this.details = details;
    }

}
