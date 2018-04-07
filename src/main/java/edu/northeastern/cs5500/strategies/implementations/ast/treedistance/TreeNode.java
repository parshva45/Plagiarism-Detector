package edu.northeastern.cs5500.strategies.implementations.ast.treedistance;

import java.util.List;

/**
 * A node belonging to a tree structure, with corresponding collection of its children, the pointer to its parent and
 * a transformation cost function for comparison with other tree nodes.
 */
public interface TreeNode {

    /**
     * Returns a collection of children of this node, each children having this node as its parent.
     * @return a collection of children of this node
     */
    List<? extends TreeNode> getChildren();

    /**
     * Returns the parent node of this tree node or null if this node is the root of the tree structure.
     * @return the parent node of this tree node or null if this tree node is the root of the tree
     */
    TreeNode getParent();

    /**
     * Returns the position of <code>child</code> among its siblings.
     * @param child the child {@link TreeNode}
     * @return the position of child
     */
    int positionOfChild(TreeNode child);

    /**
     * Returns the cost of transforming this tree node using operation {@code operation};
     * <br><br>
     * If {@code operation} equals {@link TreeOperation#OP_DELETE_NODE} then {@code other} is <code>null</code> and the
     * cost of removing this tree node should be returned.
     * <br><br>
     * If {@code operation} equals {@link TreeOperation#OP_INSERT_NODE} then the cost of inserting this tree node as
     * a child of {@code other} should be returned. Note that {@code other} can also be <code>null</code>, in those
     * cases the cost of inserting a new root node should be returned.
     * <br><br>
     * If {@code operation} equals {@link TreeOperation#OP_RENAME_NODE} then the cost of renaming this tree node to
     * {@code other} tree node should be returned.
     *
     * @param operation the type of tree operation being performed
     * @param other the tree node into which this node is being transformed
     * @return the cost of transforming this tree node using specified operation
     */
    int getTransformationCost(TreeOperation operation, TreeNode other);

}
