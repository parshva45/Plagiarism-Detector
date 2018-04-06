package edu.northeastern.cs5500.strategies.implementations.ast.treeDistance;

import java.util.ArrayList;
import java.util.List;

/**
 * A single tree transformation is a part of a series of tree structure edits, which transform one tree structure into
 * another. Each tree transformation is uniquely identified by the {@link TreeOperation}, the cost of the operation and
 * corresponding {@link TreeNode}s.
 */
public class TreeTransformation implements Comparable<TreeTransformation> {

    private TreeOperation operation;

    // nodes involved in operation
    private TreeNode firstNode, secondNode;

    private int position;

    private List<TreeNode> descendants = new ArrayList<>();

    private int childrenCount;

    // the cost of performing this operation
    private int cost;

    public TreeTransformation(TreeOperation operation, int cost, TreeNode firstNode) {
        this.operation = operation;
        this.cost = cost;
        this.firstNode = firstNode;
    }

    public TreeTransformation(TreeOperation operation, int cost, TreeNode firstNode, TreeNode secondNode) {
        this.operation = operation;
        this.cost = cost;
        this.firstNode = firstNode;
        this.secondNode = secondNode;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDescendants(List<TreeNode> descendants) {
        this.descendants = descendants;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    /**
     * Return the {@link TreeOperation} associated with this tree transformation.
     * @return the {@link TreeOperation} associated with this tree transformation.
     */
    public TreeOperation getOperation() {
        return operation;
    }

    /**
     * Return the cost of this tree transformation.
     * @return the cost of this tree transformation
     */
    public int getCost() {
        return cost;
    }

    /**
     * The first node involved in this tree transformation.
     * @return the first node involved in this tree transformation
     */
    public TreeNode getFirstNode() {
        return firstNode;
    }

    /**
     * The second node involved in this tree transformation.
     * @return the second node involved in this tree transformation
     */
    public TreeNode getSecondNode() {
        return secondNode;
    }

    /**
     * Returns the position of tree node insertion for {@link TreeOperation#OP_INSERT_NODE} operation.
     * @return the position of tree node insertion for {@link TreeOperation#OP_INSERT_NODE} operation
     */
    public int getPosition() {
        return position;
    }

    /**
     * For {@link TreeOperation#OP_INSERT_NODE}, returns the list of {@link TreeNode} descendants of the tree node
     * being inserted. This is necessary in order to know which siblings of the inserted node should be demoted i.e.
     * their status changed from children to grandchildren.
     * @return the list of descendants that are candidates for demotion
     */
    public List<TreeNode> getDescendants() {
        return descendants;
    }

    /**
     * For {@link TreeOperation#OP_INSERT_NODE} returns the number of children of
     * {@link TreeTransformation#getSecondNode()}, so that the insertion position can be calculated.
     * @return the number of children of {@link TreeTransformation#getSecondNode()}
     */
    public int getChildrenCount() {
        return this.childrenCount;
    }

    @Override
    public int compareTo(TreeTransformation o) {
        return this.operation.ordinal() - o.getOperation().ordinal();
    }
}
