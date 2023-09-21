package com.example.testtask.dto;

public class Interval<T extends Comparable<T>> {
    private T start;
    private T end;

    public Interval(T start, T end) {
        this.start = start;
        this.end = end;
    }

    public T getStart() {
        return start;
    }

    public void setStart(T start) {
        if (end.compareTo(start) >= 0)
            this.start = start;
    }

    public T getEnd() {
        return end;
    }

    public void setEnd(T end) {
        if (end.compareTo(start) >= 0)
            this.end = end;
    }
}
