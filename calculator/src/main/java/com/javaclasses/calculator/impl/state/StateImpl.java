package com.javaclasses.calculator.impl.state;

import com.javaclasses.finiteStateMachine.State;

public enum StateImpl implements State {
    BINARY_OPERATION, INITIAL, NUMBER, OPEN_PARENTHESIS, CLOSE_PARENTHESIS
}
