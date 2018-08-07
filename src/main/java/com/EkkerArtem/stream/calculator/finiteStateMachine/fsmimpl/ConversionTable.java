package com.EkkerArtem.stream.calculator.finiteStateMachine.fsmimpl;

import com.EkkerArtem.stream.calculator.finiteStateMachine.NextState;
import com.EkkerArtem.stream.calculator.finiteStateMachine.State;
import com.EkkerArtem.stream.calculator.finiteStateMachine.Transition;
import com.EkkerArtem.stream.calculator.state.Operation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.EkkerArtem.stream.calculator.finiteStateMachine.State.*;

public class ConversionTable implements Transition {
    private HashMap<State, List<State>> convTable = new HashMap<>();

    private List<State> BinaryOperationConversationTable() {
        List<State> additionTable = new LinkedList<>();
        additionTable.add(OPENPARENTHESIS);
        additionTable.add(CLOSEPARENTHESIS);
        additionTable.add(NUMBER);
        return additionTable;
    }

    private List<State> InitialStateConversationTable() {
        List<State> initialStateTable = new LinkedList<>();
        initialStateTable.add(NUMBER);
        return initialStateTable;
    }

    private List<State> OpenParenthesisConversationTable() {
        List<State> openParenthesisTable = new LinkedList<>();
        openParenthesisTable.add(BINNARYOPERATION);
        openParenthesisTable.add(CLOSEPARENTHESIS);
        return openParenthesisTable;
    }

    private List<State> CloseParenthesisConversationTable() {
        List<State> closeParenthesisTable = new LinkedList<>();
        closeParenthesisTable.add(NUMBER);
        closeParenthesisTable.add(OPENPARENTHESIS);
        return closeParenthesisTable;
    }

    private List<State> NumberStateConversationTable() {
        List<State> numberStateParenthesisTable = new LinkedList<>();
        numberStateParenthesisTable.add(BINNARYOPERATION);
        numberStateParenthesisTable.add(OPENPARENTHESIS);
        numberStateParenthesisTable.add(CLOSEPARENTHESIS);
        return numberStateParenthesisTable;
    }

    private void converstaionTable() {
        convTable.put(BINNARYOPERATION, BinaryOperationConversationTable());
        convTable.put(INITIAL, InitialStateConversationTable());
        convTable.put(OPENPARENTHESIS, OpenParenthesisConversationTable());
        convTable.put(CLOSEPARENTHESIS, CloseParenthesisConversationTable());
        convTable.put(NUMBER, NumberStateConversationTable());
    }

    public ConversionTable() {
        convTable.put(BINNARYOPERATION, BinaryOperationConversationTable());
        convTable.put(INITIAL, InitialStateConversationTable());
        convTable.put(OPENPARENTHESIS, OpenParenthesisConversationTable());
        convTable.put(CLOSEPARENTHESIS, CloseParenthesisConversationTable());
        convTable.put(NUMBER, NumberStateConversationTable());
    }

    @Override
    public boolean canMove(State current, State next) {
        if (convTable.containsKey(current)) {
            List nextStages = convTable.get(current);
            if (nextStages.contains(next)) {
                return true;
            }
        }
        return false;
    }
}

