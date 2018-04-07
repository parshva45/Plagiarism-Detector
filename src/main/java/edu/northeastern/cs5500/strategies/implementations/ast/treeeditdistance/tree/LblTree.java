package edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import java.util.Enumeration;
import java.util.Vector;

/**
 * A node of a tree. Each tree has an ID.
 * The label can be empty, but can not contain trailing spaces (nor consist only of spaces).
 * 
 * Two nodes are equal, if there labels are equal, and n1 < n2 if label(n1) < label(n2).
 *
 * @author namratabilurkar
 */
public class LblTree extends DefaultMutableTreeNode implements Comparable {
	
	public static final String OPEN_BRACKET = "{";
	public static final String CLOSE_BRACKET = "}";
	public static final String ID_SEPARATOR = ":";
	

	int treeID = Node.NO_TREE_ID;
	String label = null;
	Object tmpData = null;
	int nodeID = Node.NO_NODE;
	
	/**
	 * Use only this constructor!
	 */
	public LblTree(String label, int treeID) {
		super();
		this.treeID = treeID;
		this.label = label;
	}
		
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String showNode() {
		return label;
	}
	
	public int getTreeID() {
		if (isRoot()) {
			return treeID;
		} else {
			return ((LblTree)getRoot()).getTreeID();
		}
	}
	

	/**
	 * tmpData: Hook for any data that a method must attach to a tree.
	 * Methods can assume, that this date is null and should return it
	 * to be null!
	 */
	public void setTmpData(Object tmpData) {
		this.tmpData = tmpData;
	}
	
	public Object getTmpData() {
		return tmpData;
	}
	
	/**
	 * Clear tmpData in subtree rooted in this node.
	 */
	public void clearTmpData() {
		for (Enumeration e = breadthFirstEnumeration(); e.hasMoreElements();) {
			((LblTree)e.nextElement()).setTmpData(null);
		}
	}

	/**
	 * @return node count
	 */
	public int getNodeCount() {
		int sum = 1;
		for (Enumeration e = children(); e.hasMoreElements();) {
			sum += ((LblTree)e.nextElement()).getNodeCount();
		}
		return sum;
	}
	

	/**
	 * Constructs an LblTree from a string representation of tree. The
	 * treeID in the String representation is optional; if no treeID is given,
	 * the treeID of the returned tree will be NO_ID.
	 *
	 * @param s string representation of a tree. Format: "treeID:{root{...}}".
	 * @return tree represented by s
	 */
	public static LblTree fromString(String s) {
		int treeID = FormatUtilities.getTreeID(s);
		s = s.substring(s.indexOf(OPEN_BRACKET), s.lastIndexOf(CLOSE_BRACKET) + 1);
		LblTree node = new LblTree(FormatUtilities.getRoot(s), treeID);
		Vector c = FormatUtilities.getChildren(s);
		for (int i = 0; i < c.size(); i++) {
			node.add(fromString((String)c.elementAt(i)));
		}
		return node;
	}
	
	/**
	 * String representation of a tree. Reverse operation of {@link #fromString(String)}.
	 * treeID is NO_ID, it is skiped in the string representation.
	 *
	 * @return string representation of this tree
	 *
	 */
	@Override
	public String toString() {	
		String res = OPEN_BRACKET + showNode();
		if ((getTreeID() >= 0) && (isRoot())) {
			res = getTreeID() + ID_SEPARATOR + res;
		}
		for (Enumeration e = children(); e.hasMoreElements();) {
			res += ((LblTree)e.nextElement()).toString();
		}
		res += CLOSE_BRACKET;
		return res;
	}   

	/**
	 * Returns the number of the node in the sibling set.
	 * 
	 * {@link DefaultMutableTreeNode.getIndex(DefaultMutableTreeNode)} fails
	 * if nodes have the same label, as it uses the {@link #equals(Object)} method
	 * to find a node. This method compares the object addresses.
	 *
	 */
	@Override
	public int getIndex(TreeNode n) {
		for (int i = 0; i < this.getChildCount(); i++) {
			if (this.getChildAt(i) == n) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Compares the labels.
	 */
	public int compareTo(Object o) {		
		return getLabel().compareTo(((LblTree)o).getLabel());
	}
	

	/**
	 * Compares the labels.
	 */
	@Override
	public boolean equals(Object o) {
		return this.compareTo(o) == 0;
	}


}
