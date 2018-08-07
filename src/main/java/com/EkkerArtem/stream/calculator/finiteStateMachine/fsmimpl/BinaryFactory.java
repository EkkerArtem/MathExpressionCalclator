package com.EkkerArtem.stream.calculator.finiteStateMachine.fsmimpl;

import com.EkkerArtem.stream.calculator.state.Operation;
import com.EkkerArtem.stream.calculator.state.impl.Addition;
import com.EkkerArtem.stream.calculator.state.impl.Division;
import com.EkkerArtem.stream.calculator.state.impl.Multiplication;
import com.EkkerArtem.stream.calculator.state.impl.Subtraction;

public class BinaryFactory {
    public Operation operationFactory(String value) {
        switch (value) {
            case "+": {
                return new Addition();
            }
            case "-": {
                return new Subtraction();
            }
            case "*": {
                return new Multiplication();
            }
            case "/": {
                return new Division();
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }
}