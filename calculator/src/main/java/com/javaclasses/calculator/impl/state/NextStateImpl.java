package com.javaclasses.calculator.impl.state;

import com.javaclasses.finiteStateMachine.NextState;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;

public class NextStateImpl implements NextState {

    private static final Map<String, StateImpl> registry =
            ImmutableMap.<String, StateImpl>builder()
                    .put("+", StateImpl.BINARY_OPERATION)
                    .put("-", StateImpl.BINARY_OPERATION)
                    .put("*", StateImpl.BINARY_OPERATION)
                    .put("/", StateImpl.BINARY_OPERATION)
                    .put("(", StateImpl.OPEN_PARENTHESIS)
                    .put(")", StateImpl.CLOSE_PARENTHESIS)
                    .build();

    @Override
    public StateImpl getNextState(String value) {
        if (NumberUtils.isNumber(value)) {
            return StateImpl.NUMBER;
        }
        StateImpl stateImpl = registry.get(value);
        if (stateImpl == null) {
            throw new UnsupportedOperationException(value);
        }
        return stateImpl;
    }
}
