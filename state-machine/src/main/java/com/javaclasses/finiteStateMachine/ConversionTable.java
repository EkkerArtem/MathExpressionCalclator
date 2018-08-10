package com.javaclasses.finiteStateMachine;

/**
 *
 *Class that is storing transition table of states.
 *
 */
public interface ConversionTable {
    /**
     * @param currentStateImpl current state of the state machine.
     * @param wantedStateImpl next wanted state of the state machine.
     * @return true if current state can be changed to next or not.
     */
    public boolean canMove(State currentStateImpl, State wantedStateImpl);
}
