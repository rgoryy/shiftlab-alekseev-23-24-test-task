package com.example.testtask.parser;

import com.example.testtask.entity.CharInterval;
import com.example.testtask.exception.IntervalNullValueDetected;
import com.example.testtask.exception.NotLetterException;
import com.example.testtask.exception.WrongArrayListElementLengthException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
            if (interval.size() == 2) {
                if (interval.get(0) == null || interval.get(1) == null)
                    throw new IntervalNullValueDetected();
                if (Character.isLetter(interval.get(0)) && Character.isLetter(interval.get(1)))
                    intervals.add(new CharInterval(interval));
                else
                    throw new JsonSyntaxException("Provided interval: " + interval + " contains not letter value");
            }
            else
                throw new WrongArrayListElementLengthException();
        }
        return intervals;
    }

    public String parseFromInterval(CharInterval interval) {
        if (interval == null)
            return ""; //todo
        return "[" + interval.getStartI() + ", " + interval.getEndI() + "]";
    }

}
