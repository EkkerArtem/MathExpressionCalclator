package com.EkkerArtem.stream.calculator.state.impl;

import com.EkkerArtem.stream.calculator.IsValid;
import com.EkkerArtem.stream.calculator.state.State;
import org.apache.commons.lang3.math.NumberUtils;

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
        if(NumberUtils.isNumber(expr)){
            return new NumberState();
        }else if(IsValid.isOpenParentheses(expr)){
            return new OpenParenthesis();
        }
        throw new IllegalArgumentException("Invalid state \'" + expr + "\' after open Parenthesis");
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(priority, o.getPriority());
    }
}
