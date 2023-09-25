package com.example.testtask.controller;

import com.example.testtask.entity.CharInterval;
import com.example.testtask.entity.DigitInterval;
import com.example.testtask.service.CharIntervalService;
import com.example.testtask.service.DigitIntervalService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/intervals")
public class TestTaskController {
    private final DigitIntervalService digitIntervalService;
    private final CharIntervalService charIntervalService;

    public TestTaskController(DigitIntervalService digitIntervalService, CharIntervalService charIntervalService) {
        this.digitIntervalService = digitIntervalService;
        this.charIntervalService = charIntervalService;
    }


    @GetMapping("/min")
    public ResponseEntity<?> getMinInterval(@RequestParam(value = "kind", defaultValue = "digits")
                                            String kind) {
        if (kind.equals("digits")) {
            System.out.println(digitIntervalService.getMinInterval().toString());
            return new ResponseEntity<>(
                    digitIntervalService.getMinInterval().toString(),
                    HttpStatus.OK
            );
        } else if (kind.equals("letters")) {
            return new ResponseEntity<>(
                    charIntervalService.getMinInterval().toString(),
                    HttpStatus.OK
            );
        }
        return null;
    }


    @PostMapping("/merge")
    public void mergeIntervals(@RequestParam(value = "kind", defaultValue = "digits")
                               String kind, @RequestBody String body) {
        Gson gson1 = new Gson();
        if (kind.equals("digits")) {
            Type listOfMyClassObject = new TypeToken<ArrayList<ArrayList<Integer>>>() {}.getType();
            ArrayList<ArrayList<Integer>> list = gson1.fromJson(body, listOfMyClassObject);
            List<DigitInterval> intervals = new ArrayList<>();
            for (ArrayList<Integer> l: list) {
                intervals.add(new DigitInterval(l));
            }
            digitIntervalService.saveInterval(intervals);
        } else if (kind.equals("letters")) {
            Type listOfMyClassObject = new TypeToken<ArrayList<ArrayList<Character>>>() {}.getType();
            ArrayList<ArrayList<Character> > list = gson1.fromJson(body, listOfMyClassObject);Object o1 = gson1.fromJson(body, listOfMyClassObject);
            List<CharInterval> intervals = new ArrayList<>();
            for (ArrayList<Character> l: list) {
                intervals.add(new CharInterval(l));
            }
            charIntervalService.saveInterval(intervals);
        }

    }

}
