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
	
	@Override
	public double treeDist(LblTree t1, LblTree t2) {
		if (this.isNormalized()) {
			return nonNormalizedTreeDist(t1, t2) / (t1.getNodeCount() + t2.getNodeCount());			
		} else {
			return nonNormalizedTreeDist(t1, t2);
		}
	}

	public abstract double nonNormalizedTreeDist(LblTree t1, LblTree t2);

	public double getDel() {
		return del;
	}

	public void setDel(double del) {
		this.del = del;
	}

	public double getIns() {
		return ins;
	}

	public void setIns(double ins) {
		this.ins = ins;
	}

	public double getUpdate() {
		return update;
	}

	public void setUpdate(double update) {
		this.update = update;
	}




}
