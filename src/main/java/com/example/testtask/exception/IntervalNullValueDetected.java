package com.example.testtask.exception;

public class IntervalNullValueDetected extends NullPointerException {
    public IntervalNullValueDetected() {
        super("Detected null value in the interval");
    }
}
