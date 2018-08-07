package com.EkkerArtem.stream.calculator.finiteStateMachine;


import com.EkkerArtem.stream.calculator.finiteStateMachine.fsmimpl.ConversionTable;
import com.EkkerArtem.stream.calculator.parser.Parser;

public abstract class AbstractStateMachine {
    private Parser parser;

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setInput(String input) {
        parser.setInput(input);
    }

    public void setNextStateInt(NextState nextStateInt) {
        NextState nextStateInt1 = nextStateInt;
    }

    public void setConversionTable(ConversionTable conversionTable) {
        ConversionTable conversionTable1 = conversionTable;
    }

    /**
     * Defiance behaviour of calculator depending on current state of state machine.
     * @param sign sores the current sign of input string.
     */
    protected abstract void performOperation(String sign);

    public void run() {

        String nextStr = parser.nextSign();

        performOperation(nextStr);
        while (parser.hasNext()) {

            String s = parser.nextSign();

            performOperation(s);
        }
    }
}
