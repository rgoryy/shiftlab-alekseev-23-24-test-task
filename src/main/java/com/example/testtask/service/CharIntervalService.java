package com.example.testtask.service;

import com.example.testtask.entity.CharInterval;
import com.example.testtask.entity.DigitInterval;
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
    public Optional<CharInterval> getMinInterval() {
        return charIntervalRepository.findMinInterval();
    }

    @Override
    public void saveIntervals(List<CharInterval> intervals) {
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
        for (int i = 1; i < intervals.size(); i++) {
            char currentStart = intervals.get(i).getStartI();
            char currentEnd = intervals.get(i).getEndI();
            if (currentStart <= end) {
                end = (char) Math.max(currentEnd, end);
            } else {
                mergedIntervals.add(new CharInterval(start, end));
                start = currentStart;
                end = currentEnd;
            }
        }
        mergedIntervals.add(new CharInterval(start, end));
        return mergedIntervals;
    }

    @Override
    public void sortIntervalsByStartValue(List<CharInterval> intervals) {
        intervals.sort(Comparator.comparing(CharInterval::getStartI));
    }
}
