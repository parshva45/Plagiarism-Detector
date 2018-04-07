package edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.editDistance;
import edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.tree.LblTree;
import edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.tree.TreeDist;

public abstract class EditBasedDist extends TreeDist {

	private double ins;
	private double del;
	private double update;

	public EditBasedDist(boolean normalized) {
		this(1, 1, 1, normalized);
	}
	
	public EditBasedDist(double ins, double del, double update, boolean normalized) {
		super(normalized);
		this.ins = ins;
		this.del = del;
		this.update = update;
	}

	/**
	 * Determine the tree distance
	 * @param t1 first tree
	 * @param t2 second tree
	 * @return the tree distance
	 */
	@Override
	public double treeDist(LblTree t1, LblTree t2) {
		if (this.isNormalized()) {
			return nonNormalizedTreeDist(t1, t2) / (t1.getNodeCount() + t2.getNodeCount());			
		} else {
			return nonNormalizedTreeDist(t1, t2);
		}
	}

	public abstract double nonNormalizedTreeDist(LblTree t1, LblTree t2);

	/**
	 * Getter method
	 * @return del
	 */
	public double getDel() {
		return del;
	}

	/**
	 * Setter method
	 * @param del is set
	 */
	public void setDel(double del) {
		this.del = del;
	}

	/**
	 * Getter function
	 * @return insert option
	 */
	public double getIns() {
		return ins;
	}

	/**
	 * setter method
	 * @param ins value is set
	 */
	public void setIns(double ins) {
		this.ins = ins;
	}

	/**
	 * Getter method
	 * @return update value
	 */
	public double getUpdate() {
		return update;
	}

	/**
	 * Setter method
	 * @param update value is set
	 */
	public void setUpdate(double update) {
		this.update = update;
	}




}
