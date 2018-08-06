package com.EkkerArtem.stream.calculator.state.impl;

import com.EkkerArtem.stream.calculator.state.State;

public class OpenParenthesis implements State {

    private final int priority = 1;
    private String tokenName = "(";

    @Override
    public int performOperation(Integer... args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getArgsAmount() {
        return 0;
    }

    @Override
    public String getStateName() {
        return tokenName;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public State getNextState(String expr) {
        return null;
    }

    @Override
    public int compareTo(State o) {
        return 0;
    }
}
