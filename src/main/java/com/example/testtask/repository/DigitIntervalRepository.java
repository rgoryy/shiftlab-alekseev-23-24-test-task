package com.example.testtask.repository;

import com.example.testtask.entity.DigitInterval;
import org.springframework.stereotype.Repository;


@Repository
public interface DigitIntervalRepository extends IntervalRepository<Integer, DigitInterval> {
}
