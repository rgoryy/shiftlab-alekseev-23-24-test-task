package com.example.testtask.service;

import com.example.testtask.model.Interval;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DigitsIntervalService {

    public void mergeIntervals(List<Interval> intervals) { //todo
            List<Interval> mergedIntervals = mergeIntervalsAlg(intervals);
            for (Interval i: mergedIntervals) {
                System.out.println(i.getStart() + " " + i.getEnd());
            }
    }

    private static List<Interval> mergeIntervalsAlg(List<Interval> intervals) {
        List<Interval> resIntervals = new ArrayList<>();
        sortIntervalsByStartValue(intervals);
        int start = intervals.get(0).getStart(); //todo проверка на то что лист не пустой
        int end = intervals.get(0).getEnd();
        int index = 1;
        while (index < intervals.size()) {
            int currentStart = intervals.get(index).getStart();
            int currentEnd = intervals.get(index).getEnd();
            if (currentStart <= end) {
                end = Math.max(currentEnd, end);
            } else {
                resIntervals.add(new Interval(start, end));
                start = currentStart;
                end = currentEnd;
            }
            index++;
        }
        resIntervals.add(new Interval(start, end));
        return resIntervals;
    }

    private static void sortIntervalsByStartValue(List<Interval> intervals) {
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.getStart().compareTo(o2.getStart());
            }
        });
    }
}
