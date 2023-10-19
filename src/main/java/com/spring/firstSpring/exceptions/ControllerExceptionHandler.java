package com.spring.firstSpring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PersonNotFoundException.class})
    public ResponseEntity<MyErrorMessage> resourceNotFoundException(PersonNotFoundException ex) {
        MyErrorMessage message = new MyErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

}
