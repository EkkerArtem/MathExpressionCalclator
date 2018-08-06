package com.EkkerArtem.stream.calculator.state.impl;

import com.EkkerArtem.stream.calculator.state.State;
import org.apache.commons.lang3.math.NumberUtils;

public class Initial implements State {
    @Override
    public int performOperation(Integer... args) {
        return 0;
    }

    @Override
    public int getArgsAmount() {
        return 0;
    }

    @Override
    public String getStateName() {
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public State getNextState(String expr) {
        if (NumberUtils.isNumber(expr)) {
            return new NumberState();
        }
        throw new IllegalArgumentException("Invalid state \'" + expr + "\' after initial state");
    }

    @Override
    public int compareTo(State state) {
        return 0;
    }
}
