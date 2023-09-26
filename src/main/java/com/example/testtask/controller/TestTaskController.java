package com.example.testtask.controller;

import com.example.testtask.entity.CharInterval;
import com.example.testtask.entity.DigitInterval;
import com.example.testtask.exception.WrongKindValueException;
import com.example.testtask.parser.CharIntervalParser;
import com.example.testtask.parser.DigitIntervalParser;
import com.example.testtask.service.CharIntervalService;
import com.example.testtask.service.DigitIntervalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<?> resInterval;
        String response;
        if (kind.equals("digits")) {
            resInterval = digitIntervalService.getMinInterval();
            if (resInterval.isEmpty())
                return new ResponseEntity<>("[]", HttpStatus.OK);
            DigitIntervalParser digitIntervalParser = new DigitIntervalParser();
            response = digitIntervalParser.parseFromInterval((DigitInterval) resInterval.get());
        } else if (kind.equals("letters")) {
            resInterval = charIntervalService.getMinInterval();
            if (resInterval.isEmpty())
                return new ResponseEntity<>("[]", HttpStatus.OK);
            CharIntervalParser charIntervalParser = new CharIntervalParser();
            response = charIntervalParser.parseFromInterval((CharInterval) resInterval.get());
        } else
            throw new WrongKindValueException();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/merge")
    public ResponseEntity<?> mergeIntervals(@RequestParam(value = "kind", defaultValue = "digits")
                                            String kind, @RequestBody String body) {
        if (kind.equals("digits")) {
            DigitIntervalParser digitIntervalParser = new DigitIntervalParser();
            List<DigitInterval> intervals = digitIntervalParser.parseIntervals(body);
            digitIntervalService.saveIntervals(intervals);
        } else if (kind.equals("letters")) {
            CharIntervalParser charIntervalParser = new CharIntervalParser();
            List<CharInterval> intervals = charIntervalParser.parseIntervals(body);
            charIntervalService.saveIntervals(intervals);
        } else throw new WrongKindValueException();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
