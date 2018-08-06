package com.EkkerArtem.stream.calculator.parser.tree;


import com.EkkerArtem.stream.calculator.state.State;

/**
 * Stores states according to their names.
 */
public interface Tree {
    /**
     * Adds state to the tree.
     *
     * @param state state to add.
     */
    void addState(State state);

    /**
     * Searches a node with letter equals to character on the current level of the tree(Child nodes of current Node).
     * Should be run multiple times until it finds something or throws an exception.
     *
     * @param character one char from name of desired node.
     * @return StateName if state exists in the tree. Null if addition search should be done.
     * @throws IllegalArgumentException if there is no nodes with given character as letter on the current level and no
     *                                  results were not found before.
     */
    String searchState(Character character) throws IllegalArgumentException;
}
