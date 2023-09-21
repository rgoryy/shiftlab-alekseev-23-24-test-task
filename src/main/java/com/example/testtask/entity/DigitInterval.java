package com.example.testtask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DigitInterval {
    @Id
    @GeneratedValue
    private Long id;
    private Integer startt;
    private Integer endd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitInterval that = (DigitInterval) o;
        return Objects.equals(id, that.id) && Objects.equals(startt, that.startt)
                && Objects.equals(endd, that.endd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startt, endd);
    }

    @Override
    public String toString() {
        return "DigitInterval{" +
                "id=" + id +
                ", start=" + startt +
                ", end=" + endd +
                '}';
    }
}
