package com.EkkerArtem.stream.calculator.impl.operations;


/**
 * Stores an algorithm which performs operations.
 */
public interface Operation extends Comparable<Operation> {
    /**
     * Performs an binary operations with two numbers.
     *
     * @param args array of arguments
     * @return result of operations between two numbers
     */
    int performOperation(Integer... args);

    /**
     * @return amount of arguments necessary to perform an operation
     */
    int getArgsAmount();

    /**
     * @return priority of the operations
     */
    int getPriority();

    @Override
    default int compareTo(Operation operation) {
        return Integer.compare(getPriority(), operation.getPriority());
    }
}

