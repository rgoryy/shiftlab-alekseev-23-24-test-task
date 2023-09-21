package com.example.testtask.controller;

import com.example.testtask.dto.Interval;
import com.example.testtask.service.IntervalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/intervals")
public class TestTaskController {
    private final IntervalService<Integer> digitIntervalService;
    private final IntervalService<Character> characterIntervalService;

    public TestTaskController(IntervalService<Integer> intervalService, IntervalService<Character> characterIntervalService) {
        this.digitIntervalService = intervalService;
        this.characterIntervalService = characterIntervalService;
    }

    @GetMapping("/min")
    public Interval getMinInterval(@RequestParam(value = "kind", defaultValue = "digits")
                                             String kind) {
        //todo
        return null;
    }

    //todo
    @PostMapping("/merge")
    public void mergeIntervals(@RequestParam(value = "kind", defaultValue = "digits")
                                   String kind, @RequestBody List<Interval<Integer>> intervals) {
        digitIntervalService.saveInterval(intervals);
    }

}
