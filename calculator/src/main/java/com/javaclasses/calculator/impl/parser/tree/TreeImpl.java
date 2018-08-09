package com.javaclasses.calculator.impl.parser.tree;

import java.util.LinkedList;

public class TreeImpl implements Tree {
    /**
     * Root node. Is used to begin search.
     */
    private final Node rootNode = new Node();
    /**
     * Last correspond during search.
     */
    private String lastResult = null;
    /**
     * Stores letters of traveled nodes during a search
     */
    private StringBuilder traveledNodesLetters = new StringBuilder();
    /**
     * Stores current node during the search
     */
    private Node currentNode = rootNode;

    /**
     * Wraps operation into a node
     *
     * @param operation operation to wrap
     * @return Node which contains operation
     */
    private Node wrapState(String operation) {
        Node node = new Node();
        node.setSate(operation);
        return node;
    }

    /**
     * Adds operation to the tree.
     *
     * @param operation operation to add.
     */
    @Override
    public void addState(String operation) {
        char[] nameLetters = operation.toCharArray();
        Node node = wrapState(operation);

        Node currentNode = rootNode;

        outer:
        for (int i = 0; i < nameLetters.length; i++) {
            for (Node child : currentNode.getChildNodesList()) {
                if (child.getLetter().equals(nameLetters[i])) {
                    currentNode = child;
                    continue outer;
                }
            }

            if (i != nameLetters.length - 1) {
                Node emptyNode = new Node();
                emptyNode.setLetter(nameLetters[i]);
                currentNode.addChildNode(emptyNode);
                currentNode = emptyNode;
            } else {
                currentNode.addChildNode(node);
            }

        }

    }

    /**
     * Resets service variables after a search.
     */
    private void reset() {
        lastResult = null;
        traveledNodesLetters = new StringBuilder();
        currentNode = rootNode;
    }

    /**
     * Returns result of a search and resets service variables.
     *
     * @return search result
     */
    private String getSearchResult() {
        String result = lastResult;
        reset();
        return result;
    }

    /**
     * Searches a node with letter equals to character on the current level of the tree(Child nodes of current Node).
     * Should be run multiple times until it finds something or throws an exception.
     *
     * @param character one char from name of desired node.
     * @return StateName if operation exists in the tree. Null if addition search should be done.
     * @throws IllegalArgumentException if there is no nodes with given character as letter on the current level and no
     *                                  results were not found before.
     */
    @Override
    public String searchState(Character character) {
        for (Node child : currentNode.getChildNodesList()) {
            if (child.getLetter().equals(character)) {
                traveledNodesLetters.append(character);
                currentNode = child;
                if (child.getOperation() != null && child.getChildNodesList().size() != 0) {
                    lastResult = traveledNodesLetters.toString();
                    return null;
                } else if (child.getChildNodesList().size() == 0) {
                    lastResult = traveledNodesLetters.toString();
                    return getSearchResult();
                }
            }
        }
        if (lastResult != null) {
            return getSearchResult();
        }
        reset();
        throw new IllegalArgumentException("Operand not found");
    }

    /**
     * Stores operation in the tree. Links to other nodes.
     */
    private class Node {
        /**
         * Stores StateImpl. If null means it is connection node.
         */
        private String operation;
        /**
         * Is used to identify the node.
         */
        private Character letter;
        /**
         * Child nodes.
         */
        private LinkedList<Node> childNodes = new LinkedList<>();

        void setLetter(Character character) {
            this.letter = character;
        }

        void setSate(String operation) {
            this.operation = operation;
            String name = this.operation;
            this.letter = name.charAt(name.length() - 1);
        }

        String getOperation() {
            return operation;
        }

        Character getLetter() {
            return letter;
        }

        /**
         * Adds node to child nodes.
         *
         * @param node node to add
         */
        void addChildNode(Node node) {
            childNodes.add(node);
        }

        LinkedList<Node> getChildNodesList() {
            return childNodes;
        }
    }
}
