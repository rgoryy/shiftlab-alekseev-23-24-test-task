package com.example.testtask.service;

import com.example.testtask.entity.AbstractIntervalEntity;

import java.util.List;

public interface IntervalService<T extends Comparable<T>, E extends AbstractIntervalEntity<T>> {
    E getMinInterval();

    void saveInterval(List<E> intervals);

    List<E> mergeIntervals(List<E> intervals);

    void sortIntervalsByStartValue(List<E> intervals);
}
