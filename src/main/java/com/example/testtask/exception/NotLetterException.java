package com.example.testtask.exception;


public class NotLetterException extends IllegalArgumentException {
    public NotLetterException() {
        super("The value passed is not a letter");
    }
}
