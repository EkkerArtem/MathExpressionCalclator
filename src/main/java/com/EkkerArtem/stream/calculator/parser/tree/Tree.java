package com.EkkerArtem.stream.calculator.parser.tree;


/**
 * Stores states according to their names.
 */
public interface Tree {
    /**
     * Adds operation to the tree.
     *
     * @param operation operation to add.
     */
    void addState(String operation);

    /**
     * Searches a node with letter equals to character on the current level of the tree(Child nodes of current Node).
     * Should be run multiple times until it finds something or throws an exception.
     *
     * @param character one char from name of desired node.
     * @return StateName if operations exists in the tree. Null if addition search should be done.
     * @throws IllegalArgumentException if there is no nodes with given character as letter on the current level and no
     *                                  results were not found before.
     */
    String searchState(Character character) throws IllegalArgumentException;
}
