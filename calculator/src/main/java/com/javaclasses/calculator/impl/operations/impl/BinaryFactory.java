package com.javaclasses.calculator.impl.operations.impl;

import com.javaclasses.calculator.impl.operations.Operation;
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
        if (operation == null){
            throw new UnsupportedOperationException(value);
        }
        return operation;
    }
}
