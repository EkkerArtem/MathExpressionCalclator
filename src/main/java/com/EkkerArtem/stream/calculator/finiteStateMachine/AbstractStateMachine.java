package com.EkkerArtem.stream.calculator.finiteStateMachine;


import com.EkkerArtem.stream.calculator.finiteStateMachine.fsmimpl.ConversionTable;
import com.EkkerArtem.stream.calculator.parser.Parser;

import static com.EkkerArtem.stream.calculator.finiteStateMachine.State.*;

public abstract class AbstractStateMachine {
    private NextState nextStateInt;
    private ConversionTable conversionTable;
    private Parser parser;

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setInput(String input){
        parser.setInput(input);
    }

    public void setNextStateInt(NextState nextStateInt) {
        this.nextStateInt = nextStateInt;
    }

    public void setConversionTable(ConversionTable conversionTable) {
        this.conversionTable = conversionTable;
    }

    protected abstract void performOperation(String sign);

    public void run(String input) {
        State currentState = INITIAL;
        String nextStr = parser.nextSign();
        State nextState = nextStateInt.getNextState(nextStr);
        performOperation(nextStr);
        while (parser.hasNext()){
            currentState = nextState;
            String s = parser.nextSign();
            nextState = nextStateInt.getNextState(s);
            performOperation(s);
        }
    }
}
