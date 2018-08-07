package com.EkkerArtem.stream.calculator.finiteStateMachine.fsmimpl;

import com.EkkerArtem.stream.calculator.finiteStateMachine.State;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.EkkerArtem.stream.calculator.finiteStateMachine.State.*;

/**
 * Class that is storing transition table of states.
 */
public class ConversionTable {
    private HashMap<State, List<State>> convTable = new HashMap<>();

    private List<State> binaryOperationConversationTable() {
        List<State> additionTable = new LinkedList<>();
        additionTable.add(OPEN_PARENTHESIS);
        additionTable.add(CLOSE_PARENTHESIS);
        additionTable.add(NUMBER);
        return additionTable;
    }

    private List<State> initialStateConversationTable() {
        List<State> initialStateTable = new LinkedList<>();
        initialStateTable.add(NUMBER);
        return initialStateTable;
    }

    private List<State> openParenthesisConversationTable() {
        List<State> openParenthesisTable = new LinkedList<>();
        openParenthesisTable.add(BINARY_OPERATION);
        openParenthesisTable.add(CLOSE_PARENTHESIS);
        return openParenthesisTable;
    }

    private List<State> closeParenthesisConversationTable() {
        List<State> closeParenthesisTable = new LinkedList<>();
        closeParenthesisTable.add(NUMBER);
        closeParenthesisTable.add(OPEN_PARENTHESIS);
        return closeParenthesisTable;
    }

    private List<State> numberStateConversationTable() {
        List<State> numberStateParenthesisTable = new LinkedList<>();
        numberStateParenthesisTable.add(BINARY_OPERATION);
        numberStateParenthesisTable.add(OPEN_PARENTHESIS);
        numberStateParenthesisTable.add(CLOSE_PARENTHESIS);
        return numberStateParenthesisTable;
    }

    public ConversionTable() {
        convTable.put(BINARY_OPERATION, binaryOperationConversationTable());
        convTable.put(INITIAL, initialStateConversationTable());
        convTable.put(OPEN_PARENTHESIS, openParenthesisConversationTable());
        convTable.put(CLOSE_PARENTHESIS, closeParenthesisConversationTable());
        convTable.put(NUMBER, numberStateConversationTable());
    }

}

