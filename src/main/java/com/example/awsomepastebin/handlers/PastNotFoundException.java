package com.example.awsomepastebin.handlers;

import com.example.awsomepastebin.exception.IncorrectParamException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PastNotFoundException {
    @ExceptionHandler(IncorrectParamException.class)
    public ResponseEntity<?> lessLastIndication() {return ResponseEntity.badRequest().body("This Past does not exist");}
}
