package com.EkkerArtem.stream.calculator.finiteStateMachine;


public abstract class AbstractStateMachine {

    private final Parser parser;

    protected AbstractStateMachine(Parser parser) {
        this.parser = parser;
    }

    /**
     * Defiance behaviour of calculator depending on current operations of operations machine.
     *
     * @param sign sores the current sign of input string.
     */
    protected abstract void performOperation(String sign);

    public void run() {
        String nextStr = parser.nextSign();

        performOperation(nextStr);
        while (parser.hasNext()) {

            String nextSign = parser.nextSign();

            performOperation(nextSign);
        }
    }
}
