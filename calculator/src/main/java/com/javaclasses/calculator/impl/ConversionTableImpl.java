package com.javaclasses.calculator.impl;

import com.javaclasses.finiteStateMachine.ConversionTable;
import com.javaclasses.finiteStateMachine.State;
import com.javaclasses.calculator.impl.state.StateImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that is storing transition table of states.
 */
public class ConversionTableImpl implements ConversionTable {
    private HashMap<StateImpl, List<StateImpl>> convTable = new HashMap<>();

    private List<StateImpl> binaryOperationConversationTable() {
        List<StateImpl> additionTable = new LinkedList<>();
        additionTable.add(StateImpl.OPEN_PARENTHESIS);
        additionTable.add(StateImpl.CLOSE_PARENTHESIS);
        additionTable.add(StateImpl.NUMBER);
        return additionTable;
    }

    private List<StateImpl> initialStateConversationTable() {
        List<StateImpl> initialStateImplTable = new LinkedList<>();
        initialStateImplTable.add(StateImpl.NUMBER);
        return initialStateImplTable;
    }

    private List<StateImpl> openParenthesisConversationTable() {
        List<StateImpl> openParenthesisTable = new LinkedList<>();
        openParenthesisTable.add(StateImpl.BINARY_OPERATION);
        openParenthesisTable.add(StateImpl.CLOSE_PARENTHESIS);
        return openParenthesisTable;
    }

    private List<StateImpl> closeParenthesisConversationTable() {
        List<StateImpl> closeParenthesisTable = new LinkedList<>();
        closeParenthesisTable.add(StateImpl.NUMBER);
        closeParenthesisTable.add(StateImpl.OPEN_PARENTHESIS);
        return closeParenthesisTable;
    }

    private List<StateImpl> numberStateConversationTable() {
        List<StateImpl> numberStateImplParenthesisTable = new LinkedList<>();
        numberStateImplParenthesisTable.add(StateImpl.BINARY_OPERATION);
        numberStateImplParenthesisTable.add(StateImpl.OPEN_PARENTHESIS);
        numberStateImplParenthesisTable.add(StateImpl.CLOSE_PARENTHESIS);
        return numberStateImplParenthesisTable;
    }

    public ConversionTableImpl() {
        convTable.put(StateImpl.BINARY_OPERATION, binaryOperationConversationTable());
        convTable.put(StateImpl.INITIAL, initialStateConversationTable());
        convTable.put(StateImpl.OPEN_PARENTHESIS, openParenthesisConversationTable());
        convTable.put(StateImpl.CLOSE_PARENTHESIS, closeParenthesisConversationTable());
        convTable.put(StateImpl.NUMBER, numberStateConversationTable());
    }

    @Override
    public boolean canMove(State currentStateImpl, State wantedStateImpl) {
        if (convTable.containsKey(currentStateImpl)) {
            List avalibaleStages = convTable.get(currentStateImpl);
            if (avalibaleStages.contains(wantedStateImpl)) {
                return true;
            }
        }
        return false;
    }

}

