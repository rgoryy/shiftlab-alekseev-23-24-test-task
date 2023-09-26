package com.example.testtask.exception;

public class WrongKindValueException extends IllegalArgumentException {
    public WrongKindValueException() {
        super("Kind can only be digit or char");
    }
}
