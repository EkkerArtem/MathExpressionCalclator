package com.EkkerArtem.stream.calculator.impl;

import com.EkkerArtem.stream.calculator.StreamCalculator;
import com.EkkerArtem.stream.calculator.parser.Parser;
import com.EkkerArtem.stream.calculator.parser.impl.ParserImpl;
import com.EkkerArtem.stream.calculator.state.State;
import com.EkkerArtem.stream.calculator.state.impl.*;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Stack;

public class StreamCalculatorImpl implements StreamCalculator {
    /**
     * Todo add current states support and transition matrix support
     * to validate switch between states
     *
     */
    private State currentState = new Initial();
    private final Parser parser = new ParserImpl();
    private Stack<State> operatorsStack;
    private Stack<Integer> operandsStack;

    //Todo add parentheses support

    /**
     * Add basic arithmetic operations: addition, subtraction, multiplication, division
     */
    private void addBasicArithmeticOperations(){
        parser.addOperator(new Addition());
        parser.addOperator(new Subtraction());
        parser.addOperator(new Multiplication());
        parser.addOperator(new Division());
    }
    public StreamCalculatorImpl(){
        addBasicArithmeticOperations();
    }

    /**
     * Processes all stored operations.
     */
    private void cascadeOperations(){
        while (!operatorsStack.empty()) {
            State stackState = operatorsStack.peek();
            Integer[] args = new Integer[stackState.getArgsAmount()];
            for (int i = 0; i < stackState.getArgsAmount(); i++) {
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
    private void prioritizeOperation(String operationStr){
        currentState = currentState.getNextState(operationStr);
        if(!operatorsStack.empty()){
            State stackOperation = operatorsStack.peek();
            if(stackOperation.compareTo(currentState) == 0){
                cascadeOperations();
                operatorsStack.push(currentState);
            }else if(stackOperation.compareTo(currentState) > 0){
                operatorsStack.push(currentState);
            }else if(stackOperation.compareTo(currentState) < 0){
                cascadeOperations();
                operatorsStack.push(currentState);
            }
        }else {
            operatorsStack.push(currentState);
        }
    }

    @Override
    public Integer interpret(String input) {
        operatorsStack = new Stack<>();
        operandsStack = new Stack<>();

        parser.setInput(input);
        while (parser.hasNext()){
            String token = parser.nextToken();
            if(NumberUtils.isNumber(token)){
                currentState = currentState.getNextState(token);
                operandsStack.push(Integer.parseInt(token));
            }else if(token != null && !token.equals("")) {
                prioritizeOperation(token);
            }

        }

        cascadeOperations();
        currentState = new Initial();

        return operandsStack.pop();
    }
}