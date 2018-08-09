package com.EkkerArtem.stream.calculator.finiteStateMachine.fsmimpl;

import com.EkkerArtem.stream.calculator.finiteStateMachine.NextState;
import com.EkkerArtem.stream.calculator.finiteStateMachine.State;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;

public class NextStateImpl implements NextState {

    private static final Map<String, State> registry =
            ImmutableMap.<String, State>builder()
                        .put("+", State.BINARY_OPERATION)
                        .put("-", State.BINARY_OPERATION)
                        .put("*", State.BINARY_OPERATION)
                        .put("/", State.BINARY_OPERATION)
                        .put("(", State.OPEN_PARENTHESIS)
                        .put(")", State.CLOSE_PARENTHESIS)
                        .build();

    @Override
    public State getNextState(String value) {
        if (NumberUtils.isNumber(value)) {
            return State.NUMBER;
        }
        State state = registry.get(value);
        if (state == null) {
            throw new UnsupportedOperationException(value);
        }
        return state;
    }
}
