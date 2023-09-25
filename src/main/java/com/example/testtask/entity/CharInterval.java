package com.example.testtask.entity;

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
        this.startI = rawIntervals.get(0);
        this.endI = rawIntervals.get(1);
    }

    @Override
    public void setEndI(Character endI) {
        this.endI = endI;
    }

    @Override
    public void setStartI(Character startI) {
        this.startI = startI;
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
