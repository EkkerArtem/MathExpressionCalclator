package com.EkkerArtem.stream.calculator.parser.impl;

import com.EkkerArtem.stream.calculator.parser.tree.Tree;
import com.EkkerArtem.stream.calculator.parser.tree.TreeImpl;
import com.EkkerArtem.stream.calculator.parser.Parser;
import com.EkkerArtem.stream.calculator.state.State;
import com.google.common.base.Strings;

public class ParserImpl implements Parser {
    /**
     * Stores all operations and determinate if operation is supported.
     */
    private final Tree graph = new TreeImpl();
    /**
     * String to parse.
     */
    private String input;
    /**
     * Current position of parser in the string.
     */
    private int currentPosition = 0;

    /**
     * Adds support of new operand
     *
     * @param operand
     */
    @Override
    public void addOperator(State operand) {
        if (operand == null) {
            throw new NullPointerException("Given operand is empty");
        }
        if (Strings.isNullOrEmpty(operand.getStateName())) {
            throw new IllegalArgumentException("Operand name is empty or null");
        }

        graph.addState(operand);
    }

    /**
     * Validates input string
     */
    private void validateInput(String input) {
        if (input == null) {
            throw new NullPointerException("Input is null. Set input string before search");
        }
        if (input.equals("")) {
            throw new IllegalArgumentException("Input string is empty");
        }
    }

    @Override
    public void setInput(String input) {
        validateInput(input);
        this.input = input;
        this.currentPosition = 0;
    }

    /**
     * @return true if nextTokenExists or false if not
     */
    @Override
    public boolean hasNext() {
        return currentPosition < input.length();
    }

    /**
     * @return string representation of found Integer.
     */
    private String getNumber() {
        final StringBuilder resultSB = new StringBuilder();

        char currentChar = input.charAt(currentPosition);

        try {
            do {
                resultSB.append(currentChar);
                currentPosition += 1;
                currentChar = input.charAt(currentPosition);
            } while (Character.isDigit(currentChar));
        } catch (StringIndexOutOfBoundsException e) {
            return resultSB.toString();
        }

        return resultSB.toString();
    }

    /**
     * @return State name of found operation.
     */
    private String getOperandString() {
        String curResult;
        Character currentChar;

        int counter = currentPosition;
        do {
            currentChar = input.charAt(counter);
            curResult = graph.searchState(currentChar);
            counter++;
        } while (curResult == null);

        currentPosition = currentPosition + curResult.length();

        return curResult;
    }

    /**
     * @return next token in the input string.
     */
    @Override
    public String nextToken() {
        validateInput(input);

        char currentChar = input.charAt(currentPosition);
        if (Character.isDigit(currentChar)) {
            return getNumber();
        }

        return getOperandString();
    }
}
