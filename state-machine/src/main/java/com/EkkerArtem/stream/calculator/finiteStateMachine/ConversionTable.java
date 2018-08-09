package com.EkkerArtem.stream.calculator.finiteStateMachine;

public interface ConversionTable {
    public boolean canMove(State currentStateImpl, State wantedStateImpl);
}
