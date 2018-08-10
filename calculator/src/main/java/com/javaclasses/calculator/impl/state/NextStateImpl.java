package com.javaclasses.calculator.impl.state;

import com.javaclasses.calculator.impl.conversationtable.ConversionTableImpl;
import com.javaclasses.finiteStateMachine.NextState;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;

public class NextStateImpl implements NextState {


    private ConversionTableImpl conversionTable = new ConversionTableImpl();

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
        StateImpl currentState;
        if (NumberUtils.isNumber(value)) {
            currentState = StateImpl.NUMBER;
            return currentState;
        }

        currentState = StateImpl.NUMBER;
        if (conversionTable.canMove(currentState, registry.get(value))) {
            StateImpl stateImpl = registry.get(value);
            currentState = stateImpl;

            if (stateImpl == null) {
                throw new UnsupportedOperationException(value);
            }
            return currentState;
        }
       throw new IllegalArgumentException();
    }
}
