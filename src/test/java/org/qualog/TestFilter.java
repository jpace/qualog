package org.qualog;

import junit.framework.TestCase;
import org.incava.ijdk.lang.Range;

public class TestFilter extends TestCase {
    public TestFilter(String name) {
        super(name);
    }

    public void testDefaults() {
        Filter qf = new Filter(new Level(1));
        assertEquals(new Level(1), qf.getLevel());
        
        assertTrue(qf.isMatch(null, 1, null, null));
        assertTrue(qf.isMatch("",   1, null, null));
    }

    public void testFileNameMatch() {
        Filter qf = new Filter(new Level(1), "Main.java", null, null, null);
        
        assertFalse(qf.isMatch("Main2.java",       1, null, null));
        assertFalse(qf.isMatch("Mainly.java",      1, null, null));
        assertFalse(qf.isMatch("Maine.java",       1, null, null));
        assertFalse(qf.isMatch("reMain.java",      1, null, null));
        assertFalse(qf.isMatch("legerdeMain.java", 1, null, null));
        assertFalse(qf.isMatch("doMain.java",      1, null, null));

        assertTrue(qf.isMatch("Main.java",         1, null, null));
    }

    public void testLineRangeMatch() {
        Filter qf = new Filter(new Level(1), (String)null, new Range(3, 17), null, null);
        
        assertFalse(qf.isMatch(null,  0, null, null));
        assertFalse(qf.isMatch(null,  1, null, null));
        assertFalse(qf.isMatch(null,  2, null, null));
        assertFalse(qf.isMatch(null, 18, null, null));
        assertFalse(qf.isMatch(null, 19, null, null));
        assertFalse(qf.isMatch(null, 20, null, null));

        assertTrue(qf.isMatch(null,   3, null, null));
        assertTrue(qf.isMatch(null,   4, null, null));
        assertTrue(qf.isMatch(null,  11, null, null));
        assertTrue(qf.isMatch(null,  15, null, null));
        assertTrue(qf.isMatch(null,  17, null, null));
    }

    public void testClassNameMatch() {
        Filter qf = new Filter(new Level(1), null, null, "org.incava.diffj.Main", null);
        
        assertFalse(qf.isMatch(null, 1, "org.incava.diffj.Main2",   null));
        assertFalse(qf.isMatch(null, 1, "org.incava.diffj.Mainly",  null));
        assertFalse(qf.isMatch(null, 1, "org.incava.diffj.Maine",   null));
        assertFalse(qf.isMatch(null, 1, "borg.incava.diffj.Main",   null));
        assertFalse(qf.isMatch(null, 1, "cyborg.incava.diffj.Main", null));
        
        assertTrue(qf.isMatch(null,  1, "org.incava.diffj.Main",    null));
    }

    public void testMethodNameMatch() {
        Filter qf = new Filter(new Level(1), null, null, null, "init");
        
        assertFalse(qf.isMatch(null, 1, null, "initialize"));
        assertFalse(qf.isMatch(null, 1, null, "kainit"));
        assertFalse(qf.isMatch(null, 1, null, "kinit"));
        
        assertTrue(qf.isMatch(null,  1, null, "init"));
    }
}
