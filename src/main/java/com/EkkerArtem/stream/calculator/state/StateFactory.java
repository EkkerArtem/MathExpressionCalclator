package com.EkkerArtem.stream.calculator.state;

import com.EkkerArtem.stream.calculator.state.impl.*;


public class StateFactory {

    public static State getState(String exp) {
        switch (exp){
            case "+":{
                return new Addition();
            }
            case "++":{
                return new Increment();
            }
            case "-":{
                return new Subtraction();
            }
            case "*": {
                return new Multiplication();
            }
            case "/": {
                return new Division();
            }
            default:{
                throw new UnsupportedOperationException("Operation \'"+exp+"\' is not supported.");
            }
        }
    }
}
