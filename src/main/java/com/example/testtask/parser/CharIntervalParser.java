package com.example.testtask.parser;

import com.example.testtask.entity.CharInterval;
import com.example.testtask.exception.WrongArrayListLengthException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CharIntervalParser {

    public List<CharInterval> parseIntervals(String body) {
        Gson gson = new Gson();
        Type listOfIntervalsType = new TypeToken<ArrayList<ArrayList<Character>>>() {}.getType();
        ArrayList<ArrayList<Character>> intervalsList = gson.fromJson(body, listOfIntervalsType);

        List<CharInterval> intervals = new ArrayList<>();
        for (ArrayList<Character> interval : intervalsList) {
            if (interval.size() == 2)
                intervals.add(new CharInterval(interval));
            else
                throw new WrongArrayListLengthException();
        }
        return intervals;
    }

    public String parseFromInterval(CharInterval interval) {
        if (interval == null)
            return ""; //todo
        return "[" + interval.getStartI() + ", " + interval.getEndI() + "]";
    }

}
