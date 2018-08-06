package com.EkkerArtem.stream.calculator.state.impl;

import com.EkkerArtem.stream.calculator.state.State;

public class CloseParenthesis implements State {

    private final int priority = 1;
    private String tokenName = ")";

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
        switch (expr) {
            case "+": {
                return new Addition();
            }
            case "-": {
                return new Subtraction();
            }
            case "*": {
                return new Multiplication();
            }
            case "/": {
                return new Division();
            }
            case ")": {
                return new CloseParenthesis();
            }
            default: {
                throw new IllegalArgumentException("Invalid state \'" + expr + "\' after close Parenthesis");
            }
        }
    }

    @Override
    public int compareTo(State state) {
        return Integer.compare(priority, state.getPriority());
    }
}
