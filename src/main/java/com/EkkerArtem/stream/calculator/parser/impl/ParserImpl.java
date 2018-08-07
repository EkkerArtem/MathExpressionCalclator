package com.EkkerArtem.stream.calculator.parser.impl;

import com.EkkerArtem.stream.calculator.parser.tree.Tree;
import com.EkkerArtem.stream.calculator.parser.tree.TreeImpl;
import com.EkkerArtem.stream.calculator.parser.Parser;
import com.google.common.base.Strings;

public class ParserImpl implements Parser {
    /**
     * Stores all operations and determinate if operation is supported.
     */
    private final Tree graph = new TreeImpl();

    @Override
    public String getInput() {
        return input;
    }

    /**
     * String to parse.
     */
    private String input;
    /**
     * Current position of parser in the string.
     */
    private int currentPosition = 0;

    {
        addOperator("+");
        addOperator("-");
        addOperator("/");
        addOperator("*");
        addOperator("(");
        addOperator(")");
    }

    /**
     * Adds support of new operand
     *
     * @param operand
     */
    @Override
    public void addOperator(String operand) {
        if (Strings.isNullOrEmpty(operand)) {
            throw new IllegalArgumentException("Operand name is empty or null");
        }

        graph.addState(operand);
    }

    /**
     * Validates input string
     */
    private void inputValidation(String input) {
        if (input == null) {
            throw new NullPointerException("Input is null. Set input string before search");
        }
        if (input.equals("")) {
            throw new IllegalArgumentException("Input string is empty");
        }
    }

    @Override
    public void setInput(String input) {
        inputValidation(input);
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
        String currentResult;
        Character currentChararacter;

        int counter = currentPosition;
        do {
            currentChararacter = input.charAt(counter);
            currentResult = graph.searchState(currentChararacter);
            counter++;
        } while (currentResult == null);

        currentPosition = currentPosition + currentResult.length();

        return currentResult;
    }

    /**
     * @return next token in the input string.
     */
    @Override
    public String nextSign() {
        inputValidation(input);

        char currentChar = input.charAt(currentPosition);
        if (Character.isDigit(currentChar)) {
            return getNumber();
        }

        return getOperandString();
    }
}
