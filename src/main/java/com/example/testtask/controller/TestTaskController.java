package com.example.testtask.controller;

import com.example.testtask.model.Interval;
import com.example.testtask.service.DigitsIntervalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/intervals")
public class TestTaskController {
    private DigitsIntervalService digitsIntervalService;

    public TestTaskController(DigitsIntervalService digitsIntervalService) {
        this.digitsIntervalService = digitsIntervalService;
    }

    @GetMapping("/min")
    public Interval getMinInterval(@RequestParam(value = "kind", defaultValue = "digits")
                                             String kind) {
        return new Interval(1, 2);
    }

    @PostMapping("merge")
    public void mergeIntervals(@RequestBody List<Interval> intervals) {
        //todo
    }

}
