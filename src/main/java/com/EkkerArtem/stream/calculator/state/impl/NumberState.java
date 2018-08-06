package com.EkkerArtem.stream.calculator.state.impl;

import com.EkkerArtem.stream.calculator.state.State;

public class NumberState implements State {
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
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public State getNextState(String expr) {
        switch (expr){
            case "+":{
                return new Addition();
            }
            case "-":{
                return new Subtraction();
            }
            case "*": {
                return new Multiplication();
            }
            case "/": {
                return new Division();
            }
            default:{
                throw new IllegalArgumentException("Invalid state \'" + expr + "\' after number state");
            }
        }
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(getPriority(), o.getPriority());
    }
}