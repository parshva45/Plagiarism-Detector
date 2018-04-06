package edu.northeastern.cs5500.strategies.implementations.ast.treeDistance;

/**
 * A node belonging to a tree structure, which permits transformations, such as removing/adding children
 * and changing the parent node.
 * @see TreeNode
 */
public interface EditableTreeNode extends TreeNode {

    /**
     * Returns a shallow copy of this tree node, ignoring children and parent relationships.
     * @return a shallow copy of this tree node
     */
    TreeNode cloneNode();

    /**
     * Sets a new parent node of this node
     * @param newParent the new parent node
     */
    void setParent(TreeNode newParent);

    /**
     * Inserts a new child of this node at position <code>position</code>
     * @param child the child {@link TreeNode} being inserted
     * @param position the position of inserted node
     */
    void addChildAt(TreeNode child, int position);

    /**
     * Renames this tree node (changes its label) to the name of <code>other</code>
     * @param other the node which will provide the new label
     */
    void renameNodeTo(TreeNode other);

    /**
     * Removes a child node from the list of children of this tree node.
     * @param child the child node to be removed
     */
    void deleteChild(TreeNode child);

}
