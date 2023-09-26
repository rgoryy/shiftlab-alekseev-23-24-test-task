package com.example.testtask.service;

import com.example.testtask.entity.AbstractIntervalEntity;

import java.util.List;
import java.util.Optional;

public interface IntervalService<T extends Comparable<T>, E extends AbstractIntervalEntity<T>> {
    Optional<E> getMinInterval();

    void saveIntervals(List<E> intervals);

    List<E> mergeIntervals(List<E> intervals);

    void sortIntervalsByStartValue(List<E> intervals);
}
