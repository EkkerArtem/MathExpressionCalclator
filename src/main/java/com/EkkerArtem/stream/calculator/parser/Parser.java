package com.EkkerArtem.stream.calculator.parser;

import com.EkkerArtem.stream.calculator.state.State;

/**
 * Returns token by token parsing of input string.
 */
public interface Parser {
    /**
     * Adds support of new operand
     *
     * @param operand
     */
    void addOperator(State operand);

    void setInput(String input);

    /**
     * @return true if nextTokenExists or false if not
     */
    boolean hasNext();

    /**
     * @return next sign in the input string.
     */
    String nextSign();
}
