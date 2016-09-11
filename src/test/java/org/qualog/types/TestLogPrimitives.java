package org.qualog.types;

import junit.framework.TestCase;

public class TestLogPrimitives extends TestCase {
    public TestLogPrimitives(String name) {
        super(name);
    }

    public void assertUndecorated(Object obj) {
        assert(LogPrimitives.isUndecorated(obj));
    }

    public void testString() {
        assertUndecorated(String.valueOf(""));
    }

    public void testBoolean() {
        assertUndecorated(Boolean.TRUE);
    }
    
    public void testInteger() {
        assertUndecorated(Integer.valueOf(1));
    }
    
    public void testDouble() {
        assertUndecorated(Double.valueOf(3.14));
    }
    
    public void testCharacter() {
        assertUndecorated(Character.valueOf('c'));
    }
    
    public void testStackTraceElement() {
        assertUndecorated(new StackTraceElement("", "", "", 0));
    }
}
