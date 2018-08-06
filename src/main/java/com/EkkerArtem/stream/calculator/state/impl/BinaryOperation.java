package com.EkkerArtem.stream.calculator.state.impl;

import com.EkkerArtem.stream.calculator.state.State;
import org.apache.commons.lang3.math.NumberUtils;

public abstract class BinaryOperation implements State {
    @Override
    public State getNextState(String expr) {
        if (NumberUtils.isNumber(expr)) {
            return new NumberState();
        } else if (expr.equals(String.valueOf('('))) {
            return new OpenParenthesis();
        } else if (expr.equals(String.valueOf(')'))) {
            return new CloseParenthesis();
        }
        throw new IllegalArgumentException("Invalid state \'" + expr + "\' after binary operation state");
    }
}
