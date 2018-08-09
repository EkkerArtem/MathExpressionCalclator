package com.javaclasses.finiteStateMachine;

public interface ConversionTable {
    public boolean canMove(State currentStateImpl, State wantedStateImpl);
}
