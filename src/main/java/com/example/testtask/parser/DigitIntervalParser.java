package com.example.testtask.parser;

import com.example.testtask.entity.DigitInterval;
import com.example.testtask.exception.IntervalNullValueDetected;
import com.example.testtask.exception.WrongArrayListElementLengthException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DigitIntervalParser {

    public String parseFromInterval(DigitInterval interval) {
        if (interval == null)
            return "";//todo
        return "[" + interval.getStartI() + ", " + interval.getEndI() + "]";
    }


    public List<DigitInterval> parseIntervals(String body) {
        Gson gson = new Gson();
        Type listOfIntervalsType = new TypeToken<ArrayList<ArrayList<Integer>>>() {}.getType();
        ArrayList<ArrayList<Integer>> intervalsList = gson.fromJson(body, listOfIntervalsType);
        List<DigitInterval> intervals = new ArrayList<>();
        for (ArrayList<Integer> interval : intervalsList) {
            if (interval.size() == 2) {
                if (interval.get(0) == null || interval.get(1) == null)
                    throw new IntervalNullValueDetected();
                intervals.add(new DigitInterval(interval));
            }
            else
                throw new WrongArrayListElementLengthException();
        }
        return intervals;
    }
}
