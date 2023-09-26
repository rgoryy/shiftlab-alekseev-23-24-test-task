package com.example.testtask.exception;

public class EndGreaterThanStartException extends IllegalArgumentException {
    public EndGreaterThanStartException() {
        super("End value can not be greater than start value");
    }
}
