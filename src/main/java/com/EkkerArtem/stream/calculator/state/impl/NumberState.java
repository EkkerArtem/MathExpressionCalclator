package com.EkkerArtem.stream.calculator.state.impl;

import com.EkkerArtem.stream.calculator.state.State;

public class NumberState implements State {
    @Override
    public int performOperation(Integer... args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getArgsAmount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getStateName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPriority() {
        throw new UnsupportedOperationException();
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
            case "(": {
                return new OpenParenthesis();
            }
            case ")": {
                return new CloseParenthesis();
            }
            default: {
                throw new IllegalArgumentException("Invalid state \'" + expr + "\' after number state");
            }
        }
    }

    @Override
    public int compareTo(State state) {
        throw new UnsupportedOperationException();
    }
}