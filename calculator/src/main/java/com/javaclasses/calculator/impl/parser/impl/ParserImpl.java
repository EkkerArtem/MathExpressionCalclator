package com.javaclasses.calculator.impl.parser.impl;

import com.javaclasses.calculator.impl.parser.tree.Tree;
import com.javaclasses.calculator.impl.parser.tree.TreeImpl;
import com.javaclasses.finiteStateMachine.Parser;
import com.google.common.base.Strings;

public class ParserImpl implements Parser {
    /**
     * Stores all operations and determinate if operation is supported.
     */
    private final Tree graph = new TreeImpl();

    /**
     * String with math expression to parse.
     * It can be like 1+1 or 2*(1+2/(15/5)-7).
     */
    private final String input;
    /**
     * Current position of parser in the string.
     */
    private int currentPosition = 0;

    public ParserImpl(String input) {
        validateInput(input);
        this.input = input;

        initializeOperations();
    }

    private void initializeOperations() {
        addOperator("+");
        addOperator("-");
        addOperator("/");
        addOperator("*");
        addOperator("(");
        addOperator(")");
    }

    /**
     * Adds support of new operand.
     *
     * @param operand is the sign of the operand added.
     */
    @Override
    public void addOperator(String operand) {
        if (Strings.isNullOrEmpty(operand)) {
            throw new IllegalArgumentException("Operand name is empty or null");
        }
        graph.addState(operand);
    }

    /**
     * Validates input string.
     */
    private static void validateInput(String input) {
        if (input == null) {
            throw new NullPointerException("Input is null. Set input string before search");
        }
        if (input.equals("")) {
            throw new IllegalArgumentException("Input string is empty");
        }
    }

    /**
     * @return true if nextTokenExists or false if not.
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
            } while (Character.isDigit(currentChar) || currentChar == '.');
        } catch (StringIndexOutOfBoundsException e) {
            return resultSB.toString();
        }

        return resultSB.toString();
    }

    /**
     * @return StateImpl name of found operation.
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
        validateInput(input);

        char currentChar = input.charAt(currentPosition);
        if (Character.isDigit(currentChar)) {
            return getNumber();
        }

        return getOperandString();
    }
}
