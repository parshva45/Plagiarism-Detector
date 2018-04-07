package edu.northeastern.cs5500.strategies;

import junit.framework.TestCase;

import java.util.List;
import java.util.Map;
import java.util.Random;
import edu.northeastern.cs5500.strategies.implementations.ast.treedistance.*;

public class TreeDistanceTest extends TestCase {

    private static long RANDOM_SEED = 9999; // used for reproducible tests using random trees

    public void testGetPostorderIdentifiers() {
        StringTreeNode first = StringTreeNode.fromStringRepresentation("A(B(C,D,E(F)),G)");
        Map<TreeNode, Integer> postorderIds = TreeDistance.getPostorderIdentifiers(first);
        assertEquals(6, (int) postorderIds.get(first));

        StringTreeNode second = StringTreeNode.fromStringRepresentation("A(B(C))");
        postorderIds = TreeDistance.getPostorderIdentifiers(second);
        assertEquals(2, (int) postorderIds.get(second));

        StringTreeNode root = new StringTreeNode("A");
        root.addChild(new StringTreeNode("B"));
        StringTreeNode c = new StringTreeNode("C");
        root.addChild(c);
        root.addChild(new StringTreeNode("D"));
        c.addChild(new StringTreeNode("E"));
        postorderIds = TreeDistance.getPostorderIdentifiers(root);
        assertEquals(2, (int) postorderIds.get(c));
    }

    public void testLeftmostLeafDescendants() {
        StringTreeNode a = new StringTreeNode("A");
        StringTreeNode b = new StringTreeNode("B");
        StringTreeNode c = new StringTreeNode("C");
        StringTreeNode d = new StringTreeNode("D");
        StringTreeNode e = new StringTreeNode("E");
        StringTreeNode f = new StringTreeNode("F");

        a.addChild(b);
        b.addChild(c);
        a.addChild(d);
        a.addChild(e);
        e.addChild(f);

        Map<TreeNode, Integer> postorderIds = TreeDistance.getPostorderIdentifiers(a);
        TreeNode[] lmld = TreeDistance.leftmostLeafDescendants(a, postorderIds);

        assertEquals(0, (int) postorderIds.get(lmld[5]));
        assertEquals(2, (int) postorderIds.get(lmld[2]));
        assertEquals(0, (int) postorderIds.get(lmld[1]));
        assertEquals(3, (int) postorderIds.get(lmld[3]));
        assertEquals(3, (int) postorderIds.get(lmld[4]));
    }

    public void testGetKeyroots() {
        StringTreeNode a = new StringTreeNode("A");
        StringTreeNode b = new StringTreeNode("B");
        StringTreeNode c = new StringTreeNode("C");
        StringTreeNode d = new StringTreeNode("D");
        StringTreeNode e = new StringTreeNode("E");
        StringTreeNode f = new StringTreeNode("F");

        a.addChild(b);
        b.addChild(c);
        a.addChild(d);
        a.addChild(e);
        e.addChild(f);

        Map<TreeNode, Integer> postorderIds = TreeDistance.getPostorderIdentifiers(a);
        List<TreeNode> keyRoots = TreeDistance.getKeyroots(a, postorderIds);

        assertEquals(3, keyRoots.size());
        assertEquals(2, (int) postorderIds.get(keyRoots.get(0)));
        assertEquals(4, (int) postorderIds.get(keyRoots.get(1)));
        assertEquals(5, (int) postorderIds.get(keyRoots.get(2)));
    }

    private static void assertTreesMatchAfterTransformation(String a, String b) {
        StringTreeNode t1 = StringTreeNode.fromStringRepresentation(a),
                t2 = StringTreeNode.fromStringRepresentation(b);

        List<TreeTransformation> tr = TreeDistance.treeDistanceZhangShasha(t1, t2);

        t1 = (StringTreeNode) TreeDistance.transformTree(t1, tr);
        assertEquals(b, t1.toTreeString());

        t1 = StringTreeNode.fromStringRepresentation(a);
        tr = TreeDistance.treeDistanceZhangShasha(t2, t1);
        t2 = (StringTreeNode) TreeDistance.transformTree(t2, tr);
        assertEquals(a, t2.toTreeString());
    }

    public void testTransformTree() {
        assertTreesMatchAfterTransformation("4(1,2,3)", "5(3(1,2),4)");
        assertTreesMatchAfterTransformation("a(b(d,e),c(f,g))", "a(b(c(d,e,f)))");
        assertTreesMatchAfterTransformation("a(b(c,d),e(f(i,j),g,h(k)))", "a(b(c(d(e))))");
        assertTreesMatchAfterTransformation("a(b(c(d(e))))", "a(b(c,d),e(f(i,j),g,h(k)))");
        assertTreesMatchAfterTransformation("a(d)", "a(b,c,d)");
        assertTreesMatchAfterTransformation("f(d(a,c(b)),e)", "f(c(d(a,b)),e)");
        assertTreesMatchAfterTransformation("a(b)", "a(b)");
        assertTreesMatchAfterTransformation("a(b,b,c(c))", "d(a,a,a)");
        assertTreesMatchAfterTransformation("f(d(a,c(b)),e)", "f(c(d(a,b)),e)");

        Random r = new Random();
        r.setSeed(RANDOM_SEED);
        for (int i = 0; i < 100; i++) {
            assertTreesMatchAfterTransformation(StringTreeNode.randomTree(3, 8, r).toTreeString(),
                    StringTreeNode.randomTree(6, 2, r).toTreeString());
        }
    }

    private static int treeDistance(List<TreeTransformation> transformations) {
        int sum = 0;
        for (TreeTransformation t : transformations)
            sum += t.getCost();

        return sum;
    }

    private static void assertTreeDistanceEquals(String a, String b, int expected) {
        StringTreeNode t1 = StringTreeNode.fromStringRepresentation(a),
                t2 = StringTreeNode.fromStringRepresentation(b);

        assertEquals(expected, TreeDistance.treeDistanceZhangShasha((TreeNode) t1, (TreeNode) t2));
    }

    public void testTreeDistance() {
        assertTreeDistanceEquals("a(b)", "a(b)", 0);
        assertTreeDistanceEquals("a(c)", "a(d)", 1);
        assertTreeDistanceEquals("4(1,2,3)", "4(3(1,2))", 2);
        assertTreeDistanceEquals("a(b,c)", "a(b,g)", 1);

        Random r = new Random();
        r.setSeed(RANDOM_SEED);
        for (int i = 0; i < 100; i++) {
            String tree = StringTreeNode.randomTree(5, 4, r).toTreeString();
            assertTreeDistanceEquals(tree, tree, 0);
        }
    }

}
