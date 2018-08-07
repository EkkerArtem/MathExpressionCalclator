package com.EkkerArtem.stream.calculator.state.impl;

import com.EkkerArtem.stream.calculator.state.Operation;

public class Subtraction implements Operation {
    private final int priority = 4;
    private static final int argumentsAmount = 2;

    @Override
    public int getArgsAmount() {
        return argumentsAmount;
    }

    /**
     * Performs an binary state with two numbers.
     *
     * @param args array of arguments
     * @return result of subtraction between two numbers
     */
    @Override
    public int performOperation(Integer... args) {
        return args[0] - args[1];
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Operation operation) {
        return Integer.compare(priority, operation.getPriority());
    }
}

