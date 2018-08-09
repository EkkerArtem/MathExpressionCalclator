package com.EkkerArtem.stream.calculator.impl.state;

import com.EkkerArtem.stream.calculator.finiteStateMachine.State;

/**
 * The enumeration of all states.
 */
public enum StateImpl implements State {
    BINARY_OPERATION, INITIAL, NUMBER, OPEN_PARENTHESIS, CLOSE_PARENTHESIS
}
