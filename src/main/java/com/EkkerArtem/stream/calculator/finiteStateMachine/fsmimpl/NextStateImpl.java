package com.EkkerArtem.stream.calculator.finiteStateMachine.fsmimpl;

import com.EkkerArtem.stream.calculator.finiteStateMachine.NextState;
import com.EkkerArtem.stream.calculator.finiteStateMachine.State;
import org.apache.commons.lang3.math.NumberUtils;

public class NextStateImpl implements NextState {

    @Override
    public State getNextState(String value) {
        if (NumberUtils.isNumber(value)) {
            return State.NUMBER;
        }
        switch (value) {
            case "+": {
                return State.BINARY_OPERATION;
            }
            case "-": {
                return State.BINARY_OPERATION;
            }
            case "*": {
                return State.BINARY_OPERATION;
            }
            case "/": {
                return State.BINARY_OPERATION;
            }
            case "(": {
                return State.OPEN_PARENTHESIS;
            }
            case ")": {
                return State.CLOSE_PARENTHESIS;
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }
}
