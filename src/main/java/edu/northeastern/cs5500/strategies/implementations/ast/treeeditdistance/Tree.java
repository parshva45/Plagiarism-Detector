package edu.northeastern.cs5500.strategies.implementations.ast.treeeditdistance;

/**
 * @author namratabilurkar
 */

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;

public class Tree {
    Node root = new Node();
    /**
     * function leftMostChild() which gives the left most child
     */
    ArrayList<Integer> leftMostChildArray = new ArrayList<>();
    /**
     * list of keyRoots, i.e., nodes with a left child and the tree root
     */
    ArrayList<Integer> keyRoots = new ArrayList<>();
    /**
     * list of the labels of the nodes used for node comparison
     */
    ArrayList<String> labels = new ArrayList<>();

    static int[][] treeDistanceTable;

    /**
     * this constructor handles preorder notation. E.g., f(a b(c))
     * @param s
     * @throws IOException
     */
    public Tree(String s) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(s));
        tokenizer.nextToken();
        root = parseString(root, tokenizer);
        if (tokenizer.ttype != StreamTokenizer.TT_EOF) {
            throw new RuntimeException("Leftover token: " + tokenizer.ttype);
        }
    }

    /**
     * Parses the string
     * @param node is the node of the tree
     * @param tokenizer is the method of StreamTokenizer used to tokenize the input
     * @return the node of the tree built by parsing the input string
     * @throws IOException
     */
    private static Node parseString(Node node, StreamTokenizer tokenizer) throws IOException {
        node.label = tokenizer.sval;
        tokenizer.nextToken();
        if (tokenizer.ttype == '(') {
            tokenizer.nextToken();
            do {
                node.children.add(parseString(new Node(), tokenizer));
            } while (tokenizer.ttype != ')');
            tokenizer.nextToken();
        }
        return node;
    }

    /**
     * put together an ordered list of node labels of the tree
     */
    public void traverse() {
        traverse(root, labels);
    }

    /**
     * Traverse the tree built by the node
     * @param node is the node of the tree
     * @param labels is the list containing the labels of the AST
     * @return
     */
    private static ArrayList<String> traverse(Node node, ArrayList<String> labels) {
        for (int i = 0; i < node.children.size(); i++) {
            labels = traverse(node.children.get(i), labels);
        }
        labels.add(node.label);
        return labels;
    }

    /**
     * index each node in the tree according to traversal method
     */
    public void index() {
        index(root, 0);
    }

    /**
     * Determines the index of the node
     * @param node is the node of the tree
     * @param index is the index of the node
     * @return
     */
    private static int index(Node node, int index) {
        for (int i = 0; i < node.children.size(); i++) {
            index = index(node.children.get(i), index);
        }
        index++;
        node.index = index;
        return index;
    }

    /**
     * put together a function which gives leftMostChild()
     */
    public void leftMostChild() {
        leftMost();
        leftMostChildArray = leftMostChild(root, new ArrayList<>());
    }

    /**
     * Function to return the left most child array
     * @param node of the tree
     * @param leftMostChildArr is the left most child of the tree
     * @return
     */
    private ArrayList<Integer> leftMostChild(Node node, ArrayList<Integer> leftMostChildArr) {
        for (int i = 0; i < node.children.size(); i++) {
            leftMostChildArr = leftMostChild(node.children.get(i), leftMostChildArr);
        }
        leftMostChildArr.add(node.leftMost.index);
        return leftMostChildArr;
    }

    /**
     * Function that returns the left most child of the tree
     */
    private void leftMost() {
        leftMost(root);
    }

    /**
     * Function that returns the left most child of the tree
     * @param node is the node of the tree
     */
    private static void leftMost(Node node) {
        if (node == null)
            return;
        for (int i = 0; i < node.children.size(); i++) {
            leftMost(node.children.get(i));
        }
        if (node.children.isEmpty()) {
            node.leftMost = node;
        } else {
            node.leftMost = node.children.get(0).leftMost;
        }
    }

    /**
     * calculate the keyRoots
     */
    public void keyRoots() {
        for (int i = 0; i < leftMostChildArray.size(); i++) {
            int flag = 0;
            for (int j = i + 1; j < leftMostChildArray.size(); j++) {
                if (leftMostChildArray.get(j) == leftMostChildArray.get(i)) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                this.keyRoots.add(i + 1);
            }
        }
    }

    /**
     * Core logic of the tree distance evaluation
     * @param tree1 is the first tree
     * @param tree2 is the second tree
     * @return tree edit distance of tree1 and tree2
     */
    public static int zhangShasha(Tree tree1, Tree tree2) {
        tree1.index();
        tree1.leftMostChild();
        tree1.keyRoots();
        tree1.traverse();
        tree2.index();
        tree2.leftMostChild();
        tree2.keyRoots();
        tree2.traverse();

        ArrayList<Integer> l1 = tree1.leftMostChildArray;
        ArrayList<Integer> keyRoots1 = tree1.keyRoots;
        ArrayList<Integer> l2 = tree2.leftMostChildArray;
        ArrayList<Integer> keyRoots2 = tree2.keyRoots;

        /**
         * space complexity bottleneck of the algorithm
         */
        treeDistanceTable = new int[l1.size() + 1][l2.size() + 1];

        /**
         * Determine the values for the sub-problems and populate the table
         */
        for (int i1 = 1; i1 < keyRoots1.size() + 1; i1++) {
            for (int j1 = 1; j1 < keyRoots2.size() + 1; j1++) {
                int i = keyRoots1.get(i1 - 1);
                int j = keyRoots2.get(j1 - 1);
                treeDistanceTable[i][j] = treeDist(l1, l2, i, j, tree1, tree2);
            }
        }

        return treeDistanceTable[l1.size()][l2.size()];
    }

    /**
     * Tree distance table population
     * @param l1 left most child list for tree1
     * @param l2 left most child list for tree2
     * @param i index
     * @param j index
     * @param tree1 tree1
     * @param tree2 tree2
     * @return forest distance
     */
    private static int treeDist(ArrayList<Integer> l1, ArrayList<Integer> l2, int i, int j, Tree tree1, Tree tree2) {
        int[][] forestDist = new int[i + 1][j + 1];

        // costs of the three atomic operations
        int delete = 1;
        int insert = 1;
        int relabel = 1;

        forestDist[0][0] = 0;
        for (int i1 = l1.get(i - 1); i1 <= i; i1++) {
            forestDist[i1][0] = forestDist[i1 - 1][0] + delete;
        }
        for (int j1 = l2.get(j - 1); j1 <= j; j1++) {
            forestDist[0][j1] = forestDist[0][j1 - 1] + insert;
        }
        for (int i1 = l1.get(i - 1); i1 <= i; i1++) {
            for (int j1 = l2.get(j - 1); j1 <= j; j1++) {
                int iTemp = (l1.get(i - 1) > i1 - 1) ? 0 : i1 - 1;
                int jTemp = (l2.get(j - 1) > j1 - 1) ? 0 : j1 - 1;
                if ((l1.get(i1 - 1) == l1.get(i - 1)) && (l2.get(j1 - 1) == l2.get(j - 1))) {

                    int cost = (tree1.labels.get(i1 - 1).equals(tree2.labels.get(j1 - 1))) ? 0 : relabel;
                    forestDist[i1][j1] = Math.min(
                            Math.min(forestDist[iTemp][j1] + delete, forestDist[i1][jTemp] + insert),
                            forestDist[iTemp][jTemp] + cost);
                    treeDistanceTable[i1][j1] = forestDist[i1][j1];
                } else {
                    int i1Temp = l1.get(i1 - 1) - 1;
                    int j1Temp = l2.get(j1 - 1) - 1;

                    int iTemp2 = (l1.get(i - 1) > i1Temp) ? 0 : i1Temp;
                    int jTemp2 = (l2.get(j - 1) > j1Temp) ? 0 : j1Temp;

                    forestDist[i1][j1] = Math.min(
                            Math.min(forestDist[iTemp][j1] + delete, forestDist[i1][jTemp] + insert),
                            forestDist[iTemp2][jTemp2] + treeDistanceTable[i1][j1]);
                }
            }
        }
        return forestDist[i][j];
    }
}
