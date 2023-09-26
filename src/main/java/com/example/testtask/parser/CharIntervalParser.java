package com.example.testtask.parser;

import com.example.testtask.entity.CharInterval;
import com.example.testtask.exception.NotLetterException;
import com.example.testtask.exception.WrongArrayListElementLengthException;
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
            if (interval.size() == 2) {
                if (Character.isLetter(interval.get(0)) && Character.isLetter(interval.get(1)))
                    intervals.add(new CharInterval(interval));
                else
                    throw new NotLetterException();
            }
            else
                throw new WrongArrayListElementLengthException();

            boolean isValid = true;
            for (Character character : interval) {
                if (!Character.isLetter(character) || !Character.isLowerCase(character)) {
                    isValid = false;
                    break;
                }
            }

        }
        return intervals;
    }

    public String parseFromInterval(CharInterval interval) {
        if (interval == null)
            return ""; //todo
        return "[" + interval.getStartI() + ", " + interval.getEndI() + "]";
    }

}
