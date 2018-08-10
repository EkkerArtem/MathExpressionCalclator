package com.javaclasses.finiteStateMachine;

/**
 * Class for getting next operations.
 */
public interface NextState {
    /**
     * @param value is the sign of current operation.
     * @return the next state.
     */
    State getNextState(String value);
}
