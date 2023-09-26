package com.example.testtask.exception;

public class WrongArrayListElementLengthException extends IllegalArgumentException {
    public WrongArrayListElementLengthException() {
        super("Array can contain only 2 elements");
    }
}
