package edu.northeastern.cs5500.strategies.implementations.ast.treeDistance;

/**
 * Possible tree operations which transform one tree to another:
 * <ul>
 * <li>{@link #OP_DELETE_NODE}</li>
 * <li>{@link #OP_RENAME_NODE}</li>
 * <li>{@link #OP_INSERT_NODE}</li>
 * </ul>
 */
public enum TreeOperation {

    /**
     * Delete an existing node from the tree structure, promoting its children one level up.
     */
    OP_DELETE_NODE,

    /**
     * Rename an existing node in the tree structure to a new label.
     */
    OP_RENAME_NODE,

    /**
     * Insert a new node into the tree structure as a child of parent <code>p</code>, at specified position
     * <code>k</code>, making <code>m</code> next siblings children of the inserted node.
     */
    OP_INSERT_NODE

}
