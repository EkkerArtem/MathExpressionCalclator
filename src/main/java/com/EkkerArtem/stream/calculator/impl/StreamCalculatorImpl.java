package com.EkkerArtem.stream.calculator.impl;

import com.EkkerArtem.stream.calculator.StreamCalculator;
import com.EkkerArtem.stream.calculator.parser.Parser;
import com.EkkerArtem.stream.calculator.parser.impl.ParserImpl;
import com.EkkerArtem.stream.calculator.state.State;
import com.EkkerArtem.stream.calculator.state.StateFactory;
import com.EkkerArtem.stream.calculator.state.impl.Addition;
import com.EkkerArtem.stream.calculator.state.impl.Division;
import com.EkkerArtem.stream.calculator.state.impl.Multiplication;
import com.EkkerArtem.stream.calculator.state.impl.Subtraction;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Stack;

public class StreamCalculatorImpl implements StreamCalculator {
    /**
     * Todo add current states support and transition matrix support
     * to validate switch between states
     *
    */
    //State currentState;

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
            //Todo add unary operations support
            State stackOperation = operatorsStack.peek();
            stackOperation = operatorsStack.peek();
            Integer number2 = operandsStack.pop();
            Integer number1 = operandsStack.pop();

            int result = stackOperation.performOperation(number1, number2);

            operandsStack.push(result);

            operatorsStack.pop();
        }
    }

    /**
     * Takes an operation than according to its priority performs it or stores it, for future processing.
     *
     * @param operationStr string which contains string representation of the operation
     */
    private void prioritizeOperation(String operationStr){
        State currentOperation = StateFactory.getState(operationStr);
        if(!operatorsStack.empty()){
            State stackOperation = operatorsStack.peek();
            if(stackOperation.compareTo(currentOperation) == 0){
                cascadeOperations();
                operatorsStack.push(currentOperation);
            }else if(stackOperation.compareTo(currentOperation) > 0){
                operatorsStack.push(currentOperation);
            }else if(stackOperation.compareTo(currentOperation) < 0){
                cascadeOperations();
                operatorsStack.push(currentOperation);
            }
        }else {
            operatorsStack.push(currentOperation);
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
                operandsStack.push(Integer.parseInt(token));
            }else if(token != null && !token.equals("")) {
                prioritizeOperation(token);
            }

        }

        cascadeOperations();

        return operandsStack.pop();
    }
}
