package com.example.testtask.service;

import com.example.testtask.entity.CharInterval;
import com.example.testtask.repository.CharIntervalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CharIntervalService implements IntervalService<Character, CharInterval> {
    private final CharIntervalRepository charIntervalRepository;


    public CharIntervalService(CharIntervalRepository charIntervalRepository) {
        this.charIntervalRepository = charIntervalRepository;
    }

    @Override
    public CharInterval getMinInterval() {
        Optional<CharInterval> minInterval = charIntervalRepository.findMinInterval().stream().findFirst();
        return minInterval.orElse(null);
    }

    @Override
    public void saveInterval(List<CharInterval> intervals) {
        if (intervals.isEmpty()) {
            return;
        }
        List<CharInterval> mergedIntervals = mergeIntervals(intervals);
        charIntervalRepository.saveAll(mergedIntervals);
    }

    @Override
    public List<CharInterval> mergeIntervals(List<CharInterval> intervals) {
        List<CharInterval> mergedIntervals = new ArrayList<>();
        sortIntervalsByStartValue(intervals);
        char start = intervals.get(0).getStartI();
        char end = intervals.get(0).getEndI();
        int index = 1;
        while (index < intervals.size()) {
            char currentStart = intervals.get(index).getStartI();
            char currentEnd = intervals.get(index).getEndI();
            if (currentStart < end && currentEnd > end) {
                end = currentEnd;
            } else {
                mergedIntervals.add(new CharInterval(start, end));
                start = currentStart;
                end = currentEnd;
            }
            index++;
        }
        mergedIntervals.add(new CharInterval(start, end));
        return mergedIntervals;
    }

    @Override
    public void sortIntervalsByStartValue(List<CharInterval> intervals) {
        intervals.sort(Comparator.comparing(CharInterval::getStartI));
    }
}
