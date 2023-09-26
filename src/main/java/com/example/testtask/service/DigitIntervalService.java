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
    public Optional<DigitInterval> getMinInterval() {
        return digitIntervalRepository.findMinInterval();
    }

    @Override
    public void saveIntervals(List<DigitInterval> intervals) {
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
        for (int i = 1; i < intervals.size(); i++) {
            int currentStart = intervals.get(i).getStartI();
            int currentEnd = intervals.get(i).getEndI();
            if (currentStart <= end) {
                end = Math.max(end, currentEnd);
            } else {
                mergedIntervals.add(new DigitInterval(start, end));
                start = currentStart;
                end = currentEnd;
            }
        }
        mergedIntervals.add(new DigitInterval(start, end));
        return mergedIntervals;
    }

    @Override
    public void sortIntervalsByStartValue(List<DigitInterval> intervals) {
        intervals.sort(Comparator.comparing(DigitInterval::getStartI));
    }

}
