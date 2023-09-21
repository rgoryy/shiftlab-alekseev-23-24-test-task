package com.example.testtask.service;

import com.example.testtask.dto.Interval;
import com.example.testtask.entity.DigitInterval;
import com.example.testtask.repository.CharIntervalRepository;
import com.example.testtask.repository.DigitIntervalRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class IntervalService<T extends Comparable<T>> {
    private final DigitIntervalRepository digitIntervalRepository;
    private final CharIntervalRepository charIntervalRepository;

    @Autowired
    public IntervalService(DigitIntervalRepository digitIntervalRepository,
                           CharIntervalRepository charIntervalRepository) {
        this.digitIntervalRepository = digitIntervalRepository;
        this.charIntervalRepository = charIntervalRepository;
    }

    public List<Interval<T>> getMinInterval() {
        //todo
        return null;
    }

    public void saveInterval(List<Interval<T>> intervals) {
        if (intervals.isEmpty()) {
            return;
        }

        List<Interval<T>> mergedIntervals = mergeIntervals(intervals);

        if (intervals.get(0).getStart() instanceof Integer) {
            for (Interval<T> interval : mergedIntervals) {
                DigitInterval digitInterval = new DigitInterval();
                digitInterval.setStartt((Integer) interval.getStart());
                digitInterval.setEndd((Integer) interval.getEnd());
                digitIntervalRepository.save(digitInterval);
            }
        }

    }

    private List<Interval<T>> mergeIntervals(List<Interval<T>> intervals) {//todo
        List<Interval<T>> resIntervals = new ArrayList<>();
        sortIntervalsByStartValue(intervals);
        T start = intervals.get(0).getStart();
        T end = intervals.get(0).getEnd();
        int index = 1;
        while (index < intervals.size()) {
            T currentStart = intervals.get(index).getStart();
            T currentEnd = intervals.get(index).getEnd();
            if (currentStart.compareTo(end) <= 0) {
                if (currentEnd.compareTo(end) >= 0)
                    end = currentEnd;
            } else {
                resIntervals.add(new Interval<>(start, end));
                start = currentStart;
                end = currentEnd;
            }
            index++;
        }
        resIntervals.add(new Interval<>(start, end));
        return resIntervals;
    }

    private void sortIntervalsByStartValue(List<Interval<T>> intervals) {
        intervals.sort(new Comparator<Interval<T>>() {
            @Override
            public int compare(Interval<T> o1, Interval<T> o2) {
                return o1.getStart().compareTo(o2.getStart());
            }
        });
    }
}
