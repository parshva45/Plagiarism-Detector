package edu.northeastern.cs5500.strategies;

import edu.northeastern.cs5500.strategies.implementations.ast.treedistance.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple tree node holding a string as its label.
 */
public class StringTreeNode implements EditableTreeNode {

    private static class StringHolder {
        String str;

        public StringHolder(String str) {
            this.str = str;
        }
    }

    /**
     * Creates a string tree from a string representation of the tree. The string representation format is the
     * following: there is only one root node, the next level of the tree is denoted using brackets (, ).
     * Siblings are separated using a comma.
     * <br><br>
     * Valid examples: <code>A(B,C,D(E))</code>, <code>A(B(D(E)))</code>
     * <br><br>
     * Invalid examples: <code>A,B,C(D)</code>
     * @param tree the string used to construct the tree
     * @return a string tree from a string representation.
     */
    public static StringTreeNode fromStringRepresentation(String tree) {
        return fromStringRepresentationRec(new StringHolder(tree), null);
    }

    private static StringTreeNode fromStringRepresentationRec(StringHolder tree, StringTreeNode parent) {
        String node = "";
        while (tree.str.length() > 0) {
            char c = tree.str.charAt(0);
            tree.str = tree.str.substring(1);

            if (c == '(') {

                StringTreeNode cur = new StringTreeNode(node);
                node = "";

                fromStringRepresentationRec(tree, cur);

                if (parent == null)
                    return cur;
                else {
                    parent.addChild(cur);
                    cur.setParent(parent);
                }

            } else if (c == ',' || c == ')') {
                if (!node.equals("")) {
                    StringTreeNode cur = new StringTreeNode(node);
                    node = "";
                    parent.addChild(cur);
                    cur.setParent(parent);
                }

                if (c == ')')
                    break;

            } else {
                node += c;
            }
        }

        if (!node.equals(""))
            return new StringTreeNode(node);

        return null;
    }

    public static StringTreeNode randomTree(int maxDepth, int maxChildren, Random r) {
        StringTreeNode root = new StringTreeNode((char)(r.nextInt(26) + 'a') + "");
        randomTreeRec(root, 1, maxDepth, maxChildren, r);
        return root;
    }

    private static void randomTreeRec(StringTreeNode current, int depth, int maxDepth, int maxChildren, Random r) {
        if (depth > maxDepth)
            return;

        int nChildren = r.nextInt(maxChildren);
        for (int i = 0; i < nChildren; i++) {
            StringTreeNode child = new StringTreeNode((char)(r.nextInt(26) + 'a') + "");
            current.addChild(child);
            child.setParent(current);
            randomTreeRec(child, depth + 1, maxDepth, maxChildren, r);
        }
    }

    private List<StringTreeNode> children = new ArrayList<>();

    private StringTreeNode parent;

    private String label;

    public StringTreeNode(String label) {
        this.label = label;
    }

    public void addChild(StringTreeNode child) {
        this.children.add(child);
    }

    @Override
    public List<? extends TreeNode> getChildren() {
        return this.children;
    }

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public int getTransformationCost(TreeOperation operation, TreeNode other) {
        switch (operation) {
            case OP_DELETE_NODE:
                return 1;

            case OP_INSERT_NODE:
                return 1;

            default:
                return this.label.equals(((StringTreeNode) other).label) ? 0 : 1;
        }
    }

    @Override
    public TreeNode cloneNode() {
        return new StringTreeNode(this.label);
    }

    @Override
    public int positionOfChild(TreeNode child) {
        for (int i = 0; i < this.children.size(); i++) {
            if (this.children.get(i) == child)
                return i;
        }

        return -1;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public String toTreeString() {
        StringBuilder sb = new StringBuilder(this.label);

        if (this.children.size() > 0)
            sb.append("(");

        for (StringTreeNode child : this.children) {
            sb.append(child.toTreeString());

            if (child != this.children.get(this.children.size() - 1))
                sb.append(",");
        }

        if (this.children.size() > 0)
            sb.append(")");

        return sb.toString();
    }

    @Override
    public void setParent(TreeNode newParent) {
        this.parent = (StringTreeNode) newParent;
    }

    @Override
    public void addChildAt(TreeNode child, int position) {
        this.children.add(position, (StringTreeNode) child);
    }

    @Override
    public void renameNodeTo(TreeNode other) {
        this.label = ((StringTreeNode) other).label;
    }

    @Override
    public void deleteChild(TreeNode child) {
        this.children.remove(child);
    }
}
