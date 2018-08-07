package com.EkkerArtem.stream.calculator.finiteStateMachine;

/**
 * Class for getting next state.
 */
public interface NextState {
    /**
     * @param value is the sign of current operation.
     * @return the next state.
     */
    State getNextState(String value);
}
