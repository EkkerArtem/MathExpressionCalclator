package com.EkkerArtem.stream.calculator.impl;

import com.EkkerArtem.stream.calculator.StreamCalculator;
import com.EkkerArtem.stream.calculator.impl.parser.impl.ParserImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StreamInterpreterImplTest {

    private static Stream<Arguments> validMathematicalExpressionProvider() {
        return Stream.of(
                Arguments.of("6+2", 8), // addition test
                Arguments.of("9-3", 6), // subtraction test
                Arguments.of("4*5", 20), // multiplication test
                Arguments.of("16/4", 4), // division test
                Arguments.of("1+8*2-18/4", 13), // complex test
                Arguments.of("2*(3*4)", 24), //parenthesis test
                Arguments.of("2*(21/(3+4))",6)  //parentheses test
        );
    }

    @ParameterizedTest
    @MethodSource("validMathematicalExpressionProvider")
    void validMathematicalExpressionProvider(String input, Integer expected) {
        ParserImpl parser = new ParserImpl(input);
        StreamCalculator calculator = new StreamCalculatorImpl(parser);
        assertEquals(expected, calculator.calculate());
    }

    private static Stream<Arguments> invalidMathematicalExpressionTest() {
        return Stream.of(
                Arguments.of("smthg", "Message1"),
                Arguments.of("18+notANumber", "Message1"),
                Arguments.of("notANumber/15", "Message1"),
                Arguments.of("5+asdaf3", "Message1")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidMathematicalExpressionTest")
    void invalidMathematicalExpressionTest(String input, String message) {
        ParserImpl parser = new ParserImpl(input);
        StreamCalculator calculator = new StreamCalculatorImpl(parser);
        assertThrows(IllegalArgumentException.class, calculator::calculate, message);
    }

    @Test
    void nullCheck() {
        StreamCalculator calculator = new StreamCalculatorImpl(null);
        assertThrows(NullPointerException.class, calculator::calculate);
    }

}
