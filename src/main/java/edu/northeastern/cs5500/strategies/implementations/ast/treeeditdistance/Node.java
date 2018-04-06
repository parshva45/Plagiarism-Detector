package edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance;

/**
 * @author namratabilurkar
 */

import java.util.ArrayList;

public class Node {
    /**
     * Label of the node
     */
    public String label;
    /**
     * preorder index
     */
    public int index;
    /**
     * trees need not be binary
     */
    public ArrayList<Node> children = new ArrayList<Node>();
    /**
     * used by the recursive O(n) leftMost() function
     */
    public Node leftMost;

    public Node() {

    }

    public Node(String label) {
        this.label = label;
    }
}
