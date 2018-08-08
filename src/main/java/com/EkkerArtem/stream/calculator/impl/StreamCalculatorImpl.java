package com.EkkerArtem.stream.calculator.impl;

import com.EkkerArtem.stream.calculator.StreamCalculator;
import com.EkkerArtem.stream.calculator.finiteStateMachine.AbstractStateMachine;
import com.EkkerArtem.stream.calculator.finiteStateMachine.State;
import com.EkkerArtem.stream.calculator.finiteStateMachine.fsmimpl.BinaryFactory;
import com.EkkerArtem.stream.calculator.finiteStateMachine.fsmimpl.ConversionTable;
import com.EkkerArtem.stream.calculator.finiteStateMachine.fsmimpl.NextStateImpl;
import com.EkkerArtem.stream.calculator.parser.impl.ParserImpl;
import com.EkkerArtem.stream.calculator.state.Operation;

import java.util.Stack;

public class StreamCalculatorImpl extends AbstractStateMachine implements StreamCalculator {
    /**
     * to validate switch between states
     */
    private Operation currentOperation;
    private Stack<Operation> operatorsStack;
    private Stack<Integer> operandsStack;
    private Stack<Integer> parenthesesStack;
    private BinaryFactory binaryFactory = new BinaryFactory();

    {
        setNextStateInt(new NextStateImpl());
        setConversionTable(new ConversionTable());
        setParser(new ParserImpl());
    }

    /**
     * Processes all stored operations.
     */
    private void cascadeOperations() {
        while (!operatorsStack.empty() && (parenthesesStack.empty() || parenthesesStack.peek() < operandsStack.size() - 1)) {
            Operation stackOperation = operatorsStack.peek();
            Integer[] args = new Integer[stackOperation.getArgsAmount()];
            for (int i = stackOperation.getArgsAmount() - 1; i >= 0; i--) {
                args[i] = operandsStack.pop();
            }
            operandsStack.push(stackOperation.performOperation(args));

            operatorsStack.pop();
        }
    }

    /**
     * Takes an operation than according to its priority performs it or stores it, for future processing.
     *
     * @param operationStr string which contains string representation of the operation
     */
    private void prioritizeOperation(String operationStr) {
        currentOperation = binaryFactory.operationFactory(operationStr);
        if (!operatorsStack.empty()) {
            Operation stackOperation = operatorsStack.peek();
            if (stackOperation.compareTo(currentOperation) == 0) {
                cascadeOperations();
                operatorsStack.push(currentOperation);
            } else if (stackOperation.compareTo(currentOperation) > 0) {
                operatorsStack.push(currentOperation);
            } else if (stackOperation.compareTo(currentOperation) < 0) {
                cascadeOperations();
                operatorsStack.push(currentOperation);
            }
        } else {
            operatorsStack.push(currentOperation);
        }
    }

    @Override
    public Integer Calculate(String input) {

        operatorsStack = new Stack<>();
        operandsStack = new Stack<>();
        parenthesesStack = new Stack<>();

        setInput(input);
        run();

        if (!parenthesesStack.empty()) {
            throw new IllegalArgumentException("Parenthesis is not closed");
        }
        cascadeOperations();

        return operandsStack.pop();
    }

    @Override
    protected void performOperation(String sign) {
        NextStateImpl nState = new NextStateImpl();
        if (nState.getNextState(sign).equals(State.NUMBER)) {
            operandsStack.push(Integer.parseInt(sign));
        } else if (nState.getNextState(sign).equals(State.BINARY_OPERATION)) {
            prioritizeOperation(sign);
        } else if (nState.getNextState(sign).equals(State.OPEN_PARENTHESIS)) {
            parenthesesStack.push(operandsStack.size());
        } else if (nState.getNextState(sign).equals(State.CLOSE_PARENTHESIS)) {
            if (parenthesesStack.empty()) {
                throw new IllegalArgumentException("Parentheses is not opened");
            }
            cascadeOperations();
            parenthesesStack.pop();
        } else if (!sign.equals("")) {
            prioritizeOperation(sign);
        }
    }
}
