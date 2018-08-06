package com.EkkerArtem.stream.calculator.state.impl;

import com.EkkerArtem.stream.calculator.state.State;

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
    public int compareTo(State o) {
        return 0;
    }
}
