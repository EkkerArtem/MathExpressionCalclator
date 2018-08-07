package com.EkkerArtem.stream.calculator.finiteStateMachine;

public interface Transition {
    boolean canMove(State current, State next);
}
