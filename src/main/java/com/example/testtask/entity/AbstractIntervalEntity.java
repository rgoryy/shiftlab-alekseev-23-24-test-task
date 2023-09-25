package com.example.testtask.entity;

import jakarta.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractIntervalEntity<T extends Comparable<T>> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    protected Long id;
    public abstract void setEndI(T endI);
    public abstract void setStartI(T startI);
    public abstract T getStartI();
    public abstract T getEndI();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
