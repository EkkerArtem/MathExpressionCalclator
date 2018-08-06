package com.EkkerArtem.stream.calculator.state.impl;

import com.EkkerArtem.stream.calculator.state.State;

public class Multiplication extends BinaryOperation {
    private final int priority = 3;
    private static final int argumentsAmount = 2;
    private String tokenName = "*";

    @Override
    public int getArgsAmount() {
        return argumentsAmount;
    }

    @Override
    public String getStateName() {
        return tokenName;
    }

    /**
     * Performs an binary state with two numbers.
     *
     * @param args array of arguments
     * @return result of multiplication between two numbers
     */
    @Override
    public int performOperation(Integer... args) {
        return args[0] * args[1];
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(State state) {
        return Integer.compare(priority, state.getPriority());
    }
}