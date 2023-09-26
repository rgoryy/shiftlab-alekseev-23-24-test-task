package com.example.testtask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity(name = "digit_interval")
@NoArgsConstructor
@AllArgsConstructor
public class DigitInterval extends AbstractIntervalEntity<Integer> {
    @Column(name = "start_i")
    private Integer startI;
    @Column(name = "end_i")
    private Integer endI;

    public DigitInterval(List<Integer> rawIntervals) {
        if (rawIntervals.get(0) <= rawIntervals.get(1)) {
            this.startI = rawIntervals.get(0);
            this.endI = rawIntervals.get(1);
        } else throw new IllegalArgumentException("Invalid arguments");
    }

    @Override
    public void setEndI(Integer endI) {
        this.endI = endI; //todo not less then endI
    }

    @Override
    public void setStartI(Integer startI) {
        this.startI = startI; //todo not less then endI
    }

    @Override
    public Integer getStartI() {
        return startI;
    }

    @Override
    public Integer getEndI() {
        return endI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitInterval that = (DigitInterval) o;
        return Objects.equals(id, that.id) && Objects.equals(startI, that.startI)
                && Objects.equals(endI, that.endI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startI, endI);
    }

    @Override
    public String toString() {
        return "[" + startI + ", "+ endI +"]";
    }
}
