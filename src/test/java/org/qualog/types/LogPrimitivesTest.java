package org.qualog.types;

import junit.framework.TestCase;

public class LogPrimitivesTest extends TestCase {
    public LogPrimitivesTest(String name) {
        super(name);
    }

    // isUndecorated

    private boolean assertIsUndecorated(Object obj) {
        boolean result = LogPrimitives.isUndecorated(obj);
        assertTrue("obj: " + obj, result);
        return result;
    }

    public void testString() {
        assertIsUndecorated(String.valueOf(""));
    }

    public void testBoolean() {
        assertIsUndecorated(Boolean.TRUE);
    }
    
    public void testInteger() {
        assertIsUndecorated(Integer.valueOf(1));
    }
    
    public void testDouble() {
        assertIsUndecorated(Double.valueOf(3.14));
    }
    
    public void testCharacter() {
        assertIsUndecorated(Character.valueOf('c'));
    }
    
    public void testStackTraceElement() {
        assertIsUndecorated(new StackTraceElement("", "", "", 0));
    }
}
