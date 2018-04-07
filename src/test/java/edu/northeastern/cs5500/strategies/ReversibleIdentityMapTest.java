package edu.northeastern.cs5500.strategies;

import junit.framework.TestCase;
import edu.northeastern.cs5500.strategies.implementations.ast.treedistance.*;

public class ReversibleIdentityMapTest extends TestCase {

    private ReversibleIdentityMap<String, Integer> map;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.map = new ReversibleIdentityMap<>();
    }

    public void testPut() {
        this.map.put("a", 1);
        this.map.put("b", 2);
        this.map.put("c", 3);
        assertTrue("b".equals(this.map.getInverse(2)));
        assertTrue("c".equals(this.map.getInverse(3)));
        assertFalse("h".equals(this.map.getInverse(1)));
    }

    public void testRemove() {
        this.map.put("a", 1);
        this.map.put("b", 2);
        this.map.put("c", 3);
        this.map.remove("a");
        assertTrue(this.map.getInverse(1) == null);
        assertTrue("b".equals(this.map.getInverse(2)));
    }

    public void testPutAll() {
        this.map.put("a", 1);
        this.map.put("b", 2);
        this.map.put("c", 3);
        ReversibleIdentityMap<String, Integer> second = new ReversibleIdentityMap<>();
        second.putAll(this.map);
        assertTrue("b".equals(second.getInverse(2)));
        assertTrue("c".equals(second.getInverse(3)));
        assertFalse("h".equals(second.getInverse(1)));
    }

}
