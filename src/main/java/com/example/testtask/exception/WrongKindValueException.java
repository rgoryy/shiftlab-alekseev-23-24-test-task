package com.example.testtask.exception;

public class WrongKindValueException extends IllegalArgumentException {
    public WrongKindValueException() {
        super("Kind value can only be \"digits\" or \"letters\"");
    }
}
