package com.javaclasses.calculator.impl;

import com.javaclasses.calculator.MathExpressionCalculator;
import com.javaclasses.calculator.impl.parser.impl.ParserImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MathExpressionInterpreterImplTest {

    private static Stream<Arguments> validMathematicalExpressionProvider() {
        return Stream.of(
                Arguments.of("6+2", 8, "addition test failed"),
                Arguments.of("9-3", 6, "subtraction test failed"),
                Arguments.of("4*5", 20, "multiplication test failed"),
                Arguments.of("16/4", 4, "division test failed"),
                Arguments.of("1+8*2-18/4", 12.5, "complex test failed"),
                Arguments.of("2*(3*4)", 24, "parenthesis test failed"),
                Arguments.of("2*(21/(3+4))", 6, "parentheses test failed"),
                Arguments.of("3/2", 1.5, "double values test failed")
        );
    }

    private static Stream<Arguments> invalidMathematicalExpressionTest() {
        return Stream.of(
                Arguments.of("smthg", "invalid expression test failed"),
                Arguments.of("18+notANumber", "invalid expression test failed"),
                Arguments.of("notANumber/15", "invalid expression test failed"),
                Arguments.of("5+asdaf3", "invalid expression test failed")
        );
    }

    @ParameterizedTest
    @MethodSource("validMathematicalExpressionProvider")
    void validMathematicalExpressionProvider(String input, double expected) {
        ParserImpl parser = new ParserImpl(input);
        MathExpressionCalculator calculator = new MathExpressionCalculatorImpl(parser);
        assertEquals(expected, calculator.calculate());
    }

    @ParameterizedTest
    @MethodSource("invalidMathematicalExpressionTest")
    void invalidMathematicalExpressionTest(String input, String message) {
        ParserImpl parser = new ParserImpl(input);
        MathExpressionCalculator calculator = new MathExpressionCalculatorImpl(parser);
        assertThrows(IllegalArgumentException.class, calculator::calculate, message);
    }

    @Test
    void nullCheck() {
        MathExpressionCalculator calculator = new MathExpressionCalculatorImpl(null);
        assertThrows(NullPointerException.class, calculator::calculate);
    }

}
