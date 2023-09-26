package com.example.testtask.entity;

import com.example.testtask.exception.EndGreaterThanStartException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;

@Entity(name = "char_interval")
@NoArgsConstructor
@AllArgsConstructor
public class CharInterval extends AbstractIntervalEntity<Character> {
    @Column(name = "start_i")
    private Character startI;
    @Column(name = "end_i")
    private Character endI;

    public CharInterval(ArrayList<Character> rawIntervals) {
        if (rawIntervals.get(0) <= rawIntervals.get(1)) {
            this.startI = rawIntervals.get(0);
            this.endI = rawIntervals.get(1);
        } else throw new
                EndGreaterThanStartException();
    }

    @Override
    public void setEndI(Character endI) {
        if (startI <= endI)
            this.endI = endI;
        else throw new
                EndGreaterThanStartException();
    }

    @Override
    public void setStartI(Character startI) {
        if (startI <= endI)
            this.startI = startI;
        else throw new
                EndGreaterThanStartException();
    }

    @Override
    public Character getStartI() {
        return startI;
    }

    @Override
    public Character getEndI() {
        return endI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharInterval that = (CharInterval) o;
        return Objects.equals(id, that.id) && Objects.equals(startI, that.startI) && Objects.equals(endI, that.endI);
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
