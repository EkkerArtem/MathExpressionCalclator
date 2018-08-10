package com.javaclasses.finiteStateMachine;

/**
 * Class for getting next states and for check if state can be changed to next or not.
 */
public interface NextState {
    /**
     * @param value is the sign of current operation.
     * @return the next state.
     */
    State getNextState(String value);
}
