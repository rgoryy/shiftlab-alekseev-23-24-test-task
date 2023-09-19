package com.example.testtask.model;

public class Interval {
    private int start;
    private int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(int start) {
        if (start <= this.end)
            this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(int end) {
        if (end >= this.start)
            this.end = end;
    }
}
