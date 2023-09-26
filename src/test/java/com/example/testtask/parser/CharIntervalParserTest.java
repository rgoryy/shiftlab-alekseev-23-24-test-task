package com.example.testtask.parser;

import com.example.testtask.entity.CharInterval;
import com.example.testtask.exception.NotLetterException;
import com.example.testtask.exception.WrongArrayListElementLengthException;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CharIntervalParserTest {

    private CharIntervalParser charIntervalParser;

    @BeforeEach
    void setUp() {
        charIntervalParser = new CharIntervalParser();
    }

    @Test
    public void parseIntervalsTest_correctLine() {
        String correctLine = "[[\"a\", \"b\"], [\"c\", \"d\"]]";
        List<CharInterval> correctValues = new ArrayList<>();
        correctValues.add(new CharInterval('a', 'b'));
        correctValues.add(new CharInterval('c', 'd'));
        List<CharInterval> values = charIntervalParser.parseIntervals(correctLine);
        Assertions.assertEquals(correctValues,values);
    }

    @Test
    public void parseIntervalsTest_throwsException() {
        String notCorrectLineContainsThreeElements = "[[\"a\", \"b\", \"c\"]]";
        Assertions.assertThrows(WrongArrayListElementLengthException.class, () -> charIntervalParser.
                parseIntervals(notCorrectLineContainsThreeElements));
        String notCorrectLineContainsOneElement = "[[\"a\"]]";
        Assertions.assertThrows(WrongArrayListElementLengthException.class, () -> charIntervalParser.
                parseIntervals(notCorrectLineContainsOneElement));
    }

    @Test
    public void parseIntervalsTest_lineContainsIncludesNotLetterSymbols_throwsException() {
        String notCorrectLineContainsDigits = "[[1, 2]]";
        Assertions.assertThrows(JsonSyntaxException.class, () -> charIntervalParser.
                parseIntervals(notCorrectLineContainsDigits));
        String notCorrectLineContainsDigitAndChar = "[[1, (]]";
        Assertions.assertThrows(JsonSyntaxException.class, () -> charIntervalParser.
                parseIntervals(notCorrectLineContainsDigitAndChar));
        String notCorrectLineContainsLetterAndNotLetterSymbol = "[[\"a\", \"string\"]]";
        Assertions.assertThrows(JsonSyntaxException.class, () -> charIntervalParser.
                parseIntervals(notCorrectLineContainsLetterAndNotLetterSymbol));
    }

    @Test
    public void parseIntervalsTest_lineJsonFormatFalse_throwsException() {
        String notCorrectLine = "[{a, b}]";
        Assertions.assertThrows(JsonSyntaxException.class, () -> charIntervalParser.
                parseIntervals(notCorrectLine));
        String notCorrectLine2 = "{[a, b]}";
        Assertions.assertThrows(JsonSyntaxException.class, () -> charIntervalParser.
                parseIntervals(notCorrectLine2));
    }

}
