package com.EkkerArtem.stream.calculator.impl;

import com.EkkerArtem.stream.calculator.finiteStateMachine.ConversionTable;
import com.EkkerArtem.stream.calculator.finiteStateMachine.State;
import com.EkkerArtem.stream.calculator.impl.state.StateImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.EkkerArtem.stream.calculator.impl.state.StateImpl.*;

/**
 * Class that is storing transition table of states.
 */
public class ConversionTableImpl implements ConversionTable {
    private HashMap<StateImpl, List<StateImpl>> convTable = new HashMap<>();

    private List<StateImpl> binaryOperationConversationTable() {
        List<StateImpl> additionTable = new LinkedList<>();
        additionTable.add(OPEN_PARENTHESIS);
        additionTable.add(CLOSE_PARENTHESIS);
        additionTable.add(NUMBER);
        return additionTable;
    }

    private List<StateImpl> initialStateConversationTable() {
        List<StateImpl> initialStateImplTable = new LinkedList<>();
        initialStateImplTable.add(NUMBER);
        return initialStateImplTable;
    }

    private List<StateImpl> openParenthesisConversationTable() {
        List<StateImpl> openParenthesisTable = new LinkedList<>();
        openParenthesisTable.add(BINARY_OPERATION);
        openParenthesisTable.add(CLOSE_PARENTHESIS);
        return openParenthesisTable;
    }

    private List<StateImpl> closeParenthesisConversationTable() {
        List<StateImpl> closeParenthesisTable = new LinkedList<>();
        closeParenthesisTable.add(NUMBER);
        closeParenthesisTable.add(OPEN_PARENTHESIS);
        return closeParenthesisTable;
    }

    private List<StateImpl> numberStateConversationTable() {
        List<StateImpl> numberStateImplParenthesisTable = new LinkedList<>();
        numberStateImplParenthesisTable.add(BINARY_OPERATION);
        numberStateImplParenthesisTable.add(OPEN_PARENTHESIS);
        numberStateImplParenthesisTable.add(CLOSE_PARENTHESIS);
        return numberStateImplParenthesisTable;
    }

    public ConversionTableImpl() {
        convTable.put(BINARY_OPERATION, binaryOperationConversationTable());
        convTable.put(INITIAL, initialStateConversationTable());
        convTable.put(OPEN_PARENTHESIS, openParenthesisConversationTable());
        convTable.put(CLOSE_PARENTHESIS, closeParenthesisConversationTable());
        convTable.put(NUMBER, numberStateConversationTable());
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

