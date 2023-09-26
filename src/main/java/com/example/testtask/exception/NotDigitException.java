package com.example.testtask.exception;

public class NotDigitException extends IllegalArgumentException{
    public NotDigitException() {//todo
        super("The value passed is not a digit");
    }
}
