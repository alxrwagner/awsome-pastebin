package com.example.awsomepastebin.handlers;

import com.example.awsomepastebin.exception.PastNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IncorrectParamException {
    @ExceptionHandler(com.example.awsomepastebin.exception.IncorrectParamException.class)
    public ResponseEntity<?> paramIsNull() {return ResponseEntity.badRequest().body("The Parameter should not be missing");}

    @ExceptionHandler(PastNotFoundException.class)
    public ResponseEntity<?> isNotFoundWithThisParam() {return ResponseEntity.notFound().build();}
}
