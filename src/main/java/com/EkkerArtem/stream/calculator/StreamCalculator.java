package com.EkkerArtem.stream.calculator;

/**
 * Returns the result of calculation.
 */
public interface StreamCalculator {
    /**
     * @param input is the input expression.
     * @return the calculated answer.
     */
    Integer calculate(String input);
}
