package com.EkkerArtem.stream.calculator.impl;

import com.EkkerArtem.stream.calculator.IsValid;
import com.EkkerArtem.stream.calculator.StreamCalculator;
import com.EkkerArtem.stream.calculator.parser.Parser;
import com.EkkerArtem.stream.calculator.parser.impl.ParserImpl;
import com.EkkerArtem.stream.calculator.state.State;
import com.EkkerArtem.stream.calculator.state.impl.*;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Stack;

public class StreamCalculatorImpl implements StreamCalculator {
    /**
     * to validate switch between states
     */
    private State currentState;
    private final Parser parser = new ParserImpl();
    private Stack<State> operatorsStack;
    private Stack<Integer> operandsStack;
    private Stack<Integer> parenthesesStack;


    /**
     * Add basic arithmetic operations: addition, subtraction, multiplication, division and parenthesis.
     */
    private void addBasicArithmeticOperations() {
        parser.addOperator(new Addition());
        parser.addOperator(new Subtraction());
        parser.addOperator(new Multiplication());
        parser.addOperator(new Division());
        parser.addOperator(new OpenParenthesis());
        parser.addOperator(new CloseParenthesis());

    }

    public StreamCalculatorImpl() {
        addBasicArithmeticOperations();
    }

    /**
     * Processes all stored operations.
     */
    private void cascadeOperations() {
        while (!operatorsStack.empty() && (parenthesesStack.empty() || parenthesesStack.peek() < operandsStack.size() - 1)) {
            State stackState = operatorsStack.peek();
            Integer[] args = new Integer[stackState.getArgsAmount()];
            for (int i = stackState.getArgsAmount() - 1; i >= 0; i--) {
                args[i] = operandsStack.pop();
            }
            operandsStack.push(stackState.performOperation(args));

            operatorsStack.pop();
        }
    }

    /**
     * Takes an operation than according to its priority performs it or stores it, for future processing.
     *
     * @param operationStr string which contains string representation of the operation
     */
    private void prioritizeOperation(String operationStr) {
        currentState = currentState.getNextState(operationStr);
        if (!operatorsStack.empty()) {
            State stackOperation = operatorsStack.peek();
            if (stackOperation.compareTo(currentState) == 0) {
                cascadeOperations();
                operatorsStack.push(currentState);
            } else if (stackOperation.compareTo(currentState) > 0) {
                operatorsStack.push(currentState);
            } else if (stackOperation.compareTo(currentState) < 0) {
                cascadeOperations();
                operatorsStack.push(currentState);
            }
        } else {
            operatorsStack.push(currentState);
        }
    }

    @Override
    public Integer interpret(String input) {
        operatorsStack = new Stack<>();
        operandsStack = new Stack<>();
        parenthesesStack = new Stack<>();
        currentState = new Initial();

        parser.setInput(input);
        while (parser.hasNext()) {
            String token = parser.nextToken();
            if (NumberUtils.isNumber(token)) {
                currentState = currentState.getNextState(token);
                operandsStack.push(Integer.parseInt(token));
            } else if (IsValid.isOpenParentheses(token)) {
                parenthesesStack.push(operandsStack.size());
                currentState = currentState.getNextState(token);
            } else if (IsValid.isCloseParentheses(token)) {
                if (parenthesesStack.empty()) {
                    throw new IllegalArgumentException("Parentheses is not opened");
                }
                cascadeOperations();
                parenthesesStack.pop();
            } else if (!token.equals("")) {
                prioritizeOperation(token);
            }
        }

        if (!parenthesesStack.empty()) {
            throw new IllegalArgumentException("Parenthesis is not closed");
        }
        cascadeOperations();

        return operandsStack.pop();
    }
}