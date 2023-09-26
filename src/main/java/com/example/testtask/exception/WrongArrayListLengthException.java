package com.example.testtask.exception;

public class WrongArrayListLengthException extends IllegalArgumentException {
    public WrongArrayListLengthException() {
        super("Array can contain only 2 elements");
    }
}
