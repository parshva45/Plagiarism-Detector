package edu.northeastern.cs5500.strategies.implementations.ast.treeDistance;

import java.util.*;

/**
 * Utility class for calculating the tree distance between two tree structures.
 */
public final class TreeDistance {

    private static final int HIGH_COST = 100000;

    private TreeDistance() {
    }

    private static class IntHolder {
        public int value;
    }

    /**
     * Assigns a unique identifier to each tree node according to the postorder traversal of the tree structure.
     * Assumes that {@code node} is the root of the tree structure. All identifiers are in the range [0, number of
     * nodes in the tree).
     *
     * @param node the node from which to identify postorder numbering.
     * @return a mapping of tree nodes to postorder sequence ids
     */
    public static ReversibleIdentityMap<TreeNode, Integer> getPostorderIdentifiers(TreeNode node) {
        ReversibleIdentityMap<TreeNode, Integer> postorderMap = new ReversibleIdentityMap<>();
        getPostorderIdentifiersRec(node, postorderMap, new IntHolder());
        return postorderMap;
    }

    /**
     * Recursively assign postorder identifiers to tree nodes.
     *
     * @param current    the current tree node being processed
     * @param map        a mapping of tree nodes to postorder sequence ids
     * @param idSequence the counter of postorder sequence ids
     */
    private static void getPostorderIdentifiersRec(TreeNode current, Map<TreeNode, Integer> map,
                                                   IntHolder idSequence) {

        for (TreeNode child : current.getChildren())
            getPostorderIdentifiersRec(child, map, idSequence);

        map.put(current, idSequence.value++);
    }

    /**
     * Returns an array of leftmost leaf descendants for every node in the tree given by <code>root</code>. The
     * indexing of the returned array follows postorder IDs given in <code>postorderIDs</code>; <code>result[0]</code>
     * returns the leftmost leaf descendant of node with postorder ID zero.
     * <br><br>
     * A leftmost leaf descendant of a node is found by following the leftmost branch from the node to a leaf.
     *
     * @param root         the root node to start the search from
     * @param postorderIDs a mapping of tree nodes to postorder IDs
     * @return a list of leftmost leaf descendants for every node
     */
    public static TreeNode[] leftmostLeafDescendants(TreeNode root, Map<TreeNode, Integer> postorderIDs) {
        TreeNode[] lmld = new TreeNode[postorderIDs.get(root) + 1];
        leftmostLeafDescendantsRec(root, lmld, new ArrayList<>(), postorderIDs);
        return lmld;
    }

    /**
     * Recursively find the leftmost leaf descendants for all nodes in the tree.
     *
     * @param current      the current node being processed
     * @param ref          the list reference in which to store leftmost descendants
     * @param chain        the current path in the tree
     * @param postorderIDs a mapping of tree nodes to postorder IDs
     */
    private static void leftmostLeafDescendantsRec(TreeNode current, TreeNode[] ref, List<TreeNode> chain,
                                                   Map<TreeNode, Integer> postorderIDs) {

        if (current.getChildren().size() == 0) {
            // leftmost descendant of a leaf is the leaf itself
            ref[postorderIDs.get(current)] = current;

            // assign the rest of nodes in the chain the same leftmost leaf descendant - this leaf
            for (TreeNode ancestor : chain)
                ref[postorderIDs.get(ancestor)] = current;

        } else {
            chain.add(current);

            int i = 0;
            for (TreeNode child : current.getChildren())
                leftmostLeafDescendantsRec(child, ref, i++ == 0 ? chain : new ArrayList<>(), postorderIDs);
        }
    }

    /**
     * Returns an ordered list of keyroot nodes in the tree. A keyroot node is a node which either has a left sibling
     * or is the root of the tree. The keyroot nodes are ordered according to their postorder IDs.
     *
     * @param root         the root node to start the search from
     * @param postorderIDs a mapping of tree nodes to postorder IDs
     * @return an ordered list of keyroot nodes, ordered according to postorder IDs
     */
    public static List<TreeNode> getKeyroots(TreeNode root, Map<TreeNode, Integer> postorderIDs) {
        List<TreeNode> keyroots = new ArrayList<>();
        keyrootsRec(root, keyroots, new ArrayList<>());
        Collections.sort(keyroots, new PostorderComparator(postorderIDs));
        return keyroots;
    }

    /**
     * A comparator which sorts {@link TreeNode} objects according to their postorder IDs
     * given by a mapping.
     */
    private static class PostorderComparator implements Comparator<TreeNode> {

        private Map<TreeNode, Integer> postorderIDs;

        public PostorderComparator(Map<TreeNode, Integer> postorderIDs) {
            this.postorderIDs = postorderIDs;
        }

        @Override
        public int compare(TreeNode t1, TreeNode t2) {
            return this.postorderIDs.get(t1) - this.postorderIDs.get(t2);
        }
    }

    /**
     * Recursively find the keyroots starting from <code>current</code>.
     *
     * @param current the current node being processed
     * @param ref     the list reference in which to store keyroot nodes
     * @param chain   the current path in the tree
     */
    private static void keyrootsRec(TreeNode current, List<TreeNode> ref, List<TreeNode> chain) {
        if (current.getChildren().size() == 0) {

            if (chain.size() > 0) {
                // the first node in the chain is the keyroot node
                ref.add(chain.get(0));
            } else
                ref.add(current);

        } else {
            chain.add(current);

            int i = 0;
            for (TreeNode child : current.getChildren())
                keyrootsRec(child, ref, i++ == 0 ? chain : new ArrayList<>());
        }

    }

    /**
     * Calculates the tree distance between tree {@code t1} and {@code t2}, taking into account that both
     * trees are ordered i.e. the order of siblings is important.
     * Returns the tree distance between {@code t1} and {@code t2} i.e. the minimal sum of costs of all tree
     * transformations required to transform one tree into another.<br><br>
     *
     * For further information see paper by K. Zhang et al.:
     * <a href="http://grantjenks.com/wiki/_media/ideas/simple_fast_algorithms_for_the_editing_distance_between_tree_and_related_problems.pdf">Simple fast algorithms for the editing distance between trees and related problems</a>
     *
     * @param t1 the first tree structure
     * @param t2 the second tree structure
     * @throws IllegalArgumentException if {@code t1} or {@code t2} is {@code null}.
     * @return the tree distance between {@code t1} and {@code t2}
     */
    public static int treeDistanceZhangShasha(TreeNode t1, TreeNode t2) {
        return treeDistanceZhangShasha(t1, t2, null);
    }

    /**
     * Calculates the tree distance between tree {@code t1} and {@code t2}, taking into account that both
     * trees are ordered i.e. the order of siblings is important.
     * Returns a list of tree transformations required to transform tree {@code t1} to {@code t2}.
     * Every transformation has an associated cost. The sum of costs of all transformations is the tree distance
     * between {@code t1} and {@code t2}. The sum of costs is minimal.
     * <br><br>
     *
     * The list of transformations should be applied in the order returned, as according to K. Zhang et al.:<br><br>
     * {@code "}To construct the sequence of editing operations, simply perform all the deletes indicated by the
     * mapping (i.e., all nodes in T having no lines attached to them are deleted), then all relabellings, then all
     * inserts.{@code "}
     * <br><br>
     *
     * For further information see paper by K. Zhang et al.:
     * <a href="http://grantjenks.com/wiki/_media/ideas/simple_fast_algorithms_for_the_editing_distance_between_tree_and_related_problems.pdf">Simple fast algorithms for the editing distance between trees and related problems</a>
     *
     * @param t1 the first tree structure
     * @param t2 the second tree structure
     * @throws IllegalArgumentException if {@code t1} or {@code t2} is {@code null}.
     * @return a list of tree transformations required to transform first tree into the second
     */
    public static List<TreeTransformation> treeDistanceZhangShasha(EditableTreeNode t1, EditableTreeNode t2) {
        List<TreeTransformation> transformations = new ArrayList<>();
        treeDistanceZhangShasha(t1, t2, transformations);
        return transformations;
    }

    private static int treeDistanceZhangShasha(TreeNode t1, TreeNode t2, List<TreeTransformation> transformations) {

        if (t1 == null || t2 == null)
            throw new IllegalArgumentException("Both tree structures must not be null");

        // prepare postorder numbering
        ReversibleIdentityMap<TreeNode, Integer> postorder1 = getPostorderIdentifiers(t1),
                postorder2 = getPostorderIdentifiers(t2);

        // prepare leftmost leaf descendants
        TreeNode[] lmld1 = leftmostLeafDescendants(t1, postorder1),
                lmld2 = leftmostLeafDescendants(t2, postorder2);

        // prepare keyroots
        List<TreeNode> keyRoots1 = getKeyroots(t1, postorder1),
                keyRoots2 = getKeyroots(t2, postorder2);

        // prepare tree distance table and transformation list
        ForestTrail[][] treeDistance = new ForestTrail[postorder2.get(t2) + 1][postorder1.get(t1) + 1];

        // calculate tree distance
        for (TreeNode keyRoot1 : keyRoots1) {
            for (TreeNode keyRoot2 : keyRoots2) {
                forestDistance(keyRoot1, keyRoot2, lmld1, lmld2, postorder1, postorder2, treeDistance);
            }
        }

        if (transformations != null) {
            applyForestTrails(treeDistance[postorder2.get(t2)][postorder1.get(t1)], transformations, new
                    IdentityHashMap<>());
            Collections.sort(transformations);
        }

        return treeDistance[postorder2.get(t2)][postorder1.get(t1)].getTotalCost();
    }

    /**
     * Transforms a series of {@link ForestTrail} objects to a list of {@link TreeTransformation} objects, stored
     * in <code>ref</code>. This is needed as {@link ForestTrail} objects are used internally for storing forest
     * distances, while {@link TreeTransformation} are public.
     * @param current the current {@link ForestTrail} object in the serie
     * @param ref the list in which to store {@link TreeTransformation} objects
     * @param matchedNodes a mapping of matched/inserted nodes
     */
    private static void applyForestTrails(ForestTrail current, List<TreeTransformation> ref,
                                          IdentityHashMap<TreeNode, TreeNode> matchedNodes) {
        if (current.nextState == null)
            return;

        if (current.treeState != null) {
            applyForestTrails(current.nextState, ref, matchedNodes);
            applyForestTrails(current.treeState, ref, matchedNodes);
        } else {
            TreeTransformation t;
            switch (current.operation) {
                case OP_INSERT_NODE:
                    TreeNode clone = ((EditableTreeNode) current.first).cloneNode();
                    matchedNodes.put(current.first, clone);

                    if (current.second != null) {
                        t = new TreeTransformation(current.operation, current.cost, clone, matchedNodes.get(current
                                .second));
                        t.setPosition(current.first.getParent().positionOfChild(current.first));
                        t.setChildrenCount(current.second.getChildren().size());
                    } else
                        t = new TreeTransformation(current.operation, current.cost, clone);

                    break;

                case OP_DELETE_NODE:
                    t = new TreeTransformation(current.operation, current.cost, current.first);
                    break;

                default:
                    t = new TreeTransformation(current.operation, current.cost, current.first, current.second);
                    matchedNodes.put(current.second, current.first);
            }

            ref.add(t);
            applyForestTrails(current.nextState, ref, matchedNodes);

            if (current.operation == TreeOperation.OP_INSERT_NODE) {
                List<TreeNode> descendants = new ArrayList<>();
                populateDescendants(current.first, matchedNodes, descendants);
                t.setDescendants(descendants);
            }
        }
    }

    /**
     * Stores all descendants of tree node {@code cur} but with references to their clones, given by mapping {@code
     * map}.
     * @param cur - the current tree node being visited
     * @param map - a mapping of tree nodes to their cloned counterparts
     * @param ref - the reference list in which to store the descendants
     */
    private static void populateDescendants(TreeNode cur, IdentityHashMap<TreeNode, TreeNode> map, List<TreeNode> ref) {
        for (TreeNode child : cur.getChildren()) {
            if (map.containsKey(child))
                ref.add(map.get(child));

            populateDescendants(child, map, ref);
        }
    }

    /**
     * A class which encodes a single transformation in the forest distance table. Used for backtracking to produce a
     * series of transformations needed to transform one tree into another.
     */
    private static class ForestTrail {

        private TreeOperation operation;

        private int cost;

        private ForestTrail nextState, treeState;

        private TreeNode first, second;

        /**
         * A constructor which initializes the final forest trail state - state where both trees are empty.
         */
        public ForestTrail() {
            this.cost = 0;
        }

        public ForestTrail(TreeOperation operation, TreeNode first) {
            this.operation = operation;
            this.first = first;
            this.cost = first.getTransformationCost(operation, null);
        }

        public ForestTrail(TreeOperation operation, TreeNode first, TreeNode second) {
            this.operation = operation;
            this.first = first;
            this.second = second;
            this.cost = first.getTransformationCost(operation, second);
        }

        public ForestTrail(ForestTrail treeState, TreeNode first, TreeNode second) {
            this.operation = TreeOperation.OP_RENAME_NODE;
            this.first = first;
            this.second = second;
            this.cost = treeState.getTotalCost();
            this.treeState = treeState;
        }

        public int getTotalCost() {
            return this.cost + (this.nextState == null ? 0 : this.nextState.getTotalCost());
        }
    }

    private static void forestDistance(TreeNode keyRoot1, TreeNode keyRoot2, TreeNode[] lmld1, TreeNode[] lmld2,
                                       ReversibleIdentityMap<TreeNode, Integer> postorder1,
                                       ReversibleIdentityMap<TreeNode, Integer> postorder2, ForestTrail[][] treeDist) {

        int kr1 = postorder1.get(keyRoot1),
                kr2 = postorder2.get(keyRoot2);

        int lm1 = postorder1.get(lmld1[kr1]),
                lm2 = postorder2.get(lmld2[kr2]);

        int bound1 = kr1 - lm1 + 2;
        int bound2 = kr2 - lm2 + 2;

        // initialize forest distance table
        ForestTrail[][] forestDistance = new ForestTrail[bound2][bound1];
        forestDistance[0][0] = new ForestTrail();

        for (int i = 1, k = lm2; i < bound2; i++, k++) {
            TreeNode t = postorder2.getInverse(k);
            forestDistance[i][0] = new ForestTrail(TreeOperation.OP_INSERT_NODE, t, t.getParent());
            forestDistance[i][0].nextState = forestDistance[i - 1][0];
        }

        for (int j = 1, l = lm1; j < bound1; j++, l++) {
            TreeNode t = postorder1.getInverse(l);
            forestDistance[0][j] = new ForestTrail(TreeOperation.OP_DELETE_NODE, t);
            forestDistance[0][j].nextState = forestDistance[0][j - 1];

            // prevent removing the root node
            if (t.getParent() == null)
                forestDistance[0][j].cost = HIGH_COST;
        }

        // fill in the rest of forest distances
        for (int k = lm1, j = 1; k <= kr1; k++, j++) {
            for (int l = lm2, i = 1; l <= kr2; l++, i++) {
                TreeNode first = postorder1.getInverse(k);
                TreeNode second = postorder2.getInverse(l);

                ForestTrail insert = new ForestTrail(TreeOperation.OP_INSERT_NODE, second, second.getParent());
                insert.nextState = forestDistance[i - 1][j];

                ForestTrail delete = new ForestTrail(TreeOperation.OP_DELETE_NODE, first);
                delete.nextState = forestDistance[i][j - 1];

                // prevent removing the root node
                if (first.getParent() == null)
                    delete.cost = HIGH_COST;

                // both key roots present a tree?
                ForestTrail rename;
                boolean trees = postorder1.get(lmld1[k]).equals(lm1) && postorder2.get(lmld2[l]).equals(lm2);
                if (trees) {
                    rename = new ForestTrail(TreeOperation.OP_RENAME_NODE, first, second);
                    rename.nextState = forestDistance[i - 1][j - 1];
                } else {
                    rename = new ForestTrail(treeDist[l][k], first, second);
                    rename.nextState = forestDistance[postorder2.get(lmld2[l]) - lm2][postorder1.get(lmld1[k]) - lm1];
                }

                int min = Math.min(insert.getTotalCost(), Math.min(delete.getTotalCost(), rename.getTotalCost()));

                if (min == insert.getTotalCost())
                    forestDistance[i][j] = insert;
                else if (min == delete.getTotalCost())
                    forestDistance[i][j] = delete;
                else
                    forestDistance[i][j] = rename;

                if (trees)
                    treeDist[l][k] = forestDistance[i][j];
            }
        }
    }

    /**
     * Transform the tree given by the root node {@code root} using a list of {@code transformations}
     * obtained by the call to {@link TreeDistance#treeDistanceZhangShasha(TreeNode, TreeNode)}. This operation does
     * not produce a copy of the original tree, but makes all modifications in-place.
     * @param root the root of the tree being transformed
     * @param transformations a list of tree operations which will transform the tree
     * @return the transformed tree
     */
    public static EditableTreeNode transformTree(EditableTreeNode root, List<TreeTransformation> transformations) {
        for (TreeTransformation t : transformations) {
            switch (t.getOperation()) {
                case OP_INSERT_NODE:

                    if (t.getSecondNode() == null) {
                        // insert a new root node
                        EditableTreeNode inserted = (EditableTreeNode) t.getFirstNode();
                        inserted.addChildAt(root, 0);
                        root.setParent(inserted);
                        root = inserted;
                    } else {
                        // insert a child and make demoted siblings its new children
                        EditableTreeNode parent = (EditableTreeNode) t.getSecondNode();
                        EditableTreeNode inserted = (EditableTreeNode) t.getFirstNode();

                        List<TreeNode> toRemove = new ArrayList<>();
                        for (TreeNode child : parent.getChildren()) {
                            for (TreeNode desc : t.getDescendants()) {
                                if (desc == child) {
                                    toRemove.add(child);
                                    inserted.addChildAt(child, inserted.getChildren().size());
                                    ((EditableTreeNode) child).setParent(inserted);
                                }
                            }
                        }

                        for (TreeNode child : toRemove)
                            parent.deleteChild(child);

                        parent.addChildAt(inserted, Math.max(0, parent.getChildren().size() - t.getChildrenCount() + 1
                                + t.getPosition()));
                        inserted.setParent(parent);
                    }

                    break;

                case OP_DELETE_NODE:

                    // delete node from the tree, promoting its children
                    TreeNode deleted = t.getFirstNode();
                    int position = deleted.getParent().positionOfChild(deleted);

                    for (int i = deleted.getChildren().size() - 1; i >= 0; i--) {
                        ((EditableTreeNode) deleted.getParent()).addChildAt(deleted.getChildren().get(i), position);
                        ((EditableTreeNode) deleted.getChildren().get(i)).setParent(deleted.getParent());
                    }

                    ((EditableTreeNode) deleted.getParent()).deleteChild(deleted);

                    break;

                default:
                    EditableTreeNode first = (EditableTreeNode) t.getFirstNode(),
                            second = (EditableTreeNode) t.getSecondNode();

                    first.renameNodeTo(second);
            }
        }

        return root;
    }

}

