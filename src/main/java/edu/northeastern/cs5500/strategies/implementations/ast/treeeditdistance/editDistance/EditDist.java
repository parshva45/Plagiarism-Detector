package edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.editDistance;

import java.util.*;


import edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance.tree.LblTree;

/**
 * Zhang & Shasha algorithm
 * @author naugsten
 *
 */
public class EditDist extends EditBasedDist {
	
	// call initKeyroots to init the following arrays!
	int[] kr1;  // LR_keyroots(t1)={k | there is no k'>k such that l(k)=l(k')}
	int[] kr2;  // LR_keyroots(t2)
	int[] l1;   // l(t1) = post-order number of left-most leaf 
             	//   descendant of i-th node in post-order in t1
	int[] l2;   // l(t2)
	String[] lbl1; // label of i-th node in postorder of t1
	String[] lbl2; // label of i-th node in postorder of t2
	
	double treedist[][]; // intermediate treedist results
	
	public EditDist(boolean normalized) {
		this(1, 1, 1, normalized);
	}
	
	public EditDist(double ins, double del, double update, boolean normalized) {
		super(ins, del, update, normalized);
	}
	
	// inits kr (keyroots), l (left-most leaves), lbl (labels) with t (tree)
	private static void init(int[] kr, int[] l, String[] lbl, LblTree t) {
		int nc = l.length;
		int lc = kr.length;
		
		int i = 0;
		for (Enumeration e = t.postorderEnumeration(); e.hasMoreElements();) {
		 	LblTree n = (LblTree)e.nextElement();
			// add postorder number to node
			n.setTmpData(new Integer(i));	    	    
			// label
			lbl[i] = n.getLabel();
			// left-most leaf
			l[i] = ((Integer)((LblTree)n.getFirstLeaf()).getTmpData()).intValue();	    
			i++;
		}
		boolean[] visited = new boolean[nc];
		Arrays.fill(visited, false);
		int k = lc - 1;
		for (i = nc - 1; i >= 0; i--) {
			if (!visited[l[i]]) {
				kr[k] = i;
				visited[l[i]] = true;
				k--;
			}
		}
		t.clearTmpData();
	}
	
	@Override
	public double nonNormalizedTreeDist(LblTree t1, LblTree t2) {

		// System.out.print(t1.getTreeID() + "|" + t2.getTreeID() + "|");
		
		int nc1 = t1.getNodeCount();
		kr1 = new int[t1.getLeafCount()];
		l1 = new int[nc1];
		lbl1 = new String[nc1];
		
		int nc2 = t2.getNodeCount();
		kr2 = new int[t2.getLeafCount()];
		l2 = new int[nc2];
		lbl2 = new String[nc2];
		
		init(kr1, l1, lbl1, t1);
		init(kr2, l2, lbl2, t2);
		
		treedist = new double[nc1][nc2];
		
		for (int i = 0; i < kr1.length; i++) {
			for (int j = 0; j < kr2.length; j++) {
				treeEditDist(kr1[i], kr2[j]);
			}
		}
		// System.out.println(treedist[nc1 - 1][nc2 - 1]);
		return treedist[nc1 - 1][nc2 - 1];
	}

	
	
	private void treeEditDist(int i, int j) {
		int m = i - l1[i] + 2;
		int n = j - l2[j] + 2;
		double[][] forestdist = new double[m][n];
		
		int ioff = l1[i] - 1;
		int joff = l2[j] - 1;
		
		forestdist[0][0] = 0;
		for (int i1 = 1; i1 <= i - ioff; i1++) {
			forestdist[i1][0] = forestdist[i1 - 1][0] + this.getDel();
			//	(i1 + ioff == l1.length - 1 ? Double.POSITIVE_INFINITY : del);	
		}
		for (int j1 = 1; j1 <= j - joff; j1++) {
			forestdist[0][j1] = forestdist[0][j1 - 1] + this.getIns(); 
        	//	(j1 + joff == l2.length - 1 ? Double.POSITIVE_INFINITY : ins);	
		}
		for (int i1 = 1; i1 <= i - ioff; i1++) {
			for (int j1 = 1; j1 <= j - joff; j1++) {
				double wDel = this.getDel(); 
					// (i1 + ioff == l1.length - 1 ? Double.POSITIVE_INFINITY : del);
				double wIns = this.getIns(); 
					// (j1 + joff == l2.length - 1 ? Double.POSITIVE_INFINITY : ins);
				
				if ((l1[i1 + ioff] == l1[i]) &&
						(l2[j1 + joff] == l2[j])) {
					double u = 0;
					if (!lbl1[i1 + ioff].equals(lbl2[j1 + joff])) {
						u = this.getUpdate();
					}
					forestdist[i1][j1] = 
						Math.min(Math.min(forestdist[i1 - 1][j1] + wDel,
								forestdist[i1][j1 - 1] + wIns),
								forestdist[i1 - 1][j1 - 1] + u);
					treedist[i1 + ioff][j1 + joff] = forestdist[i1][j1];
				} else {
					forestdist[i1][j1] = 
						Math.min(Math.min(forestdist[i1 - 1][j1] + wDel,
								forestdist[i1][j1 - 1] + wIns),
								forestdist[l1[i1 + ioff] - 1 - ioff][l2[j1 + joff] -1 - joff] + 
								treedist[i1 + ioff][j1 + joff]);
				}
			}
		}		
	}
	
}
