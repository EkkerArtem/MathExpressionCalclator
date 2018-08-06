package com.EkkerArtem.stream.calculator.state.impl;

import com.EkkerArtem.stream.calculator.state.State;

public class Increment implements State {
    private static final int priority = 2;
    private static final int argumentsAmount = 1;
    private final String tokenName = "++";

    @Override
    public int performOperation(Integer... args) {
        return args[0]+1;
    }

    @Override
    public int getArgsAmount() {
        return argumentsAmount;
    }

    @Override
    public String getStateName() {
        return tokenName;
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
