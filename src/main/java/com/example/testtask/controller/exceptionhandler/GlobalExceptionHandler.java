package com.example.testtask.controller.exceptionhandler;

import com.example.testtask.exception.EndGreaterThanStartException;
import com.example.testtask.exception.WrongArrayListLengthException;
import com.example.testtask.exception.WrongKindValueException;
import com.google.gson.JsonSyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WrongKindValueException.class)
    public ResponseEntity<String> handleWrongKindValueException(WrongKindValueException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongArrayListLengthException.class)
    public ResponseEntity<String> handleWrongArrayListLengthException(WrongArrayListLengthException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonSyntaxException.class)
    public ResponseEntity<String> handleException(JsonSyntaxException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EndGreaterThanStartException.class)
    public ResponseEntity<String> handleEndGreaterThanStartException(EndGreaterThanStartException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }
}
