package com.EkkerArtem.stream.calculator.finiteStateMachine;

public interface NextState {
    State getNextState(String value);
}
