package com.EkkerArtem.stream.calculator.state.impl;

import com.EkkerArtem.stream.calculator.state.State;

public class Subtraction implements State {
    private final int priority = 4;
    private static final int argumentsAmount = 2;
    private String tokenName = "-";

    @Override
    public int getArgsAmount() {
        return argumentsAmount;
    }

    @Override
    public String getStateName() {
        return tokenName;
    }

    @Override
    public int performOperation(Integer... args) {
        return args[0] - args[1];
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(priority, o.getPriority());
    }
}
