package com.EkkerArtem.stream.calculator.state.impl;

import com.EkkerArtem.stream.calculator.state.State;
import org.apache.commons.lang3.math.NumberUtils;

public class Division implements State {
    private final int priority = 3;
    private static final int argumentsAmount = 2;
    private String tokenName = "/";

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
        return args[0] / args[1];
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public State getNextState(String expr) {
        if(NumberUtils.isNumber(expr)){
            return new NumberState();
        }
        throw new IllegalArgumentException("Invalid state \'" + expr + "\' after division state");
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(priority, o.getPriority());
    }
}