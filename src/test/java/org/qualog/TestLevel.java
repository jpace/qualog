package org.qualog;

import junit.framework.TestCase;

public class TestLevel extends TestCase {
    public TestLevel(String name) {
        super(name);
    }

    public void assertCompareTo(int exp, Integer x, Integer y) {
        assertEquals(exp, new Level(x).compareTo(new Level(y)));
    }
    
    public void testCompareTo() {
        assertCompareTo(0,   0,  0);
        assertCompareTo(0,  10, 10);
        assertCompareTo(-1,  0, 10);
        assertCompareTo(1,  10,  0);
    }

    public void assertEquals(boolean exp, Integer x, Integer y) {
        assertEquals(exp, new Level(x).equals(new Level(y)));
    }

    public void testEquals() {
        assertEquals(true,   0,  0);
        assertEquals(true,  10, 10);
        assertEquals(false,  0, 10);
        assertEquals(false, 10,  0);
    }
}
