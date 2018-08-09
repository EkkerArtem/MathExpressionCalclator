package com.EkkerArtem.stream.calculator.finiteStateMachine.fsmimpl;

import com.EkkerArtem.stream.calculator.operations.Operation;
import com.EkkerArtem.stream.calculator.operations.impl.Addition;
import com.EkkerArtem.stream.calculator.operations.impl.Division;
import com.EkkerArtem.stream.calculator.operations.impl.Multiplication;
import com.EkkerArtem.stream.calculator.operations.impl.Subtraction;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Return needed binary operation.
 */
public class BinaryFactory {

    private static final Map<String, Operation> registry =
            ImmutableMap.<String, Operation>builder()
                    .put("+", new Addition())
                    .put("-", new Subtraction())
                    .put("*", new Multiplication())
                    .put("/", new Division())
                    .build();

    /**
     * @param value the char came from parser.
     * @return needed binary operation.
     */
    public Operation operationFactory(String value) {
        Operation operation = registry.get(value);
        if (operation == null) {
            throw new UnsupportedOperationException(value);
        }
        return operation;
    }
}
