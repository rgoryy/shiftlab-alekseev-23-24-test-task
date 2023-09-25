package com.example.testtask.service;

import com.example.testtask.entity.DigitInterval;
import com.example.testtask.repository.DigitIntervalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DigitIntervalService implements IntervalService<Integer, DigitInterval> {
    private final DigitIntervalRepository digitIntervalRepository;

    public DigitIntervalService(DigitIntervalRepository digitIntervalRepository) {
        this.digitIntervalRepository = digitIntervalRepository;
    }

    @Override
    public DigitInterval getMinInterval() {
        Optional<DigitInterval> minInterval = digitIntervalRepository.findMinInterval().stream().findFirst();
        return minInterval.orElse(null);
    }

    @Override
    public void saveInterval(List<DigitInterval> intervals) {
        if (intervals.isEmpty()) {
            return;
        }
        List<DigitInterval> mergedIntervals = mergeIntervals(intervals);
        digitIntervalRepository.saveAll(mergedIntervals);
    }

    @Override
    public List<DigitInterval> mergeIntervals(List<DigitInterval> intervals) {
        List<DigitInterval> mergedIntervals = new ArrayList<>();
        sortIntervalsByStartValue(intervals);
        int start = intervals.get(0).getStartI();
        int end = intervals.get(0).getEndI();
        int index = 1;
        while (index < intervals.size()) {
            int currentStart = intervals.get(index).getStartI();
            int currentEnd = intervals.get(index).getEndI();
            if (currentStart < end && currentEnd > end) {
                end = currentEnd;
            } else {
                mergedIntervals.add(new DigitInterval(start, end));
                start = currentStart;
                end = currentEnd;
            }
            index++;
        }
        mergedIntervals.add(new DigitInterval(start, end));
        return mergedIntervals;
    }

    @Override
    public void sortIntervalsByStartValue(List<DigitInterval> intervals) {
        intervals.sort(Comparator.comparing(DigitInterval::getStartI));
    }

}
