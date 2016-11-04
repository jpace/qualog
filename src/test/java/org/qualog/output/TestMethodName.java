package org.qualog.output;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestMethodName extends TestCase {
    public TestMethodName(String name) {
        super(name);
    }

    // ctor

    public MethodName assertCtor(ANSIColor expectedColor,
                                 StackElements expectedStackElements,
                                 int expectedMethodWidth,
                                 MethodName methodName) {

        assertEquals("methodName: " + methodName, true, methodName.snipIfLong());
        assertEquals("methodName: " + methodName, true, methodName.justifyLeft());
        assertEquals("methodName: " + methodName, "TestShellComma-", methodName.getSnipped("TestShellCommand"));
        return methodName;
    }

    public void testCtorDefault() {
        ANSIColor color = null;
        StackElements stackElements = null;
        int methodWidth = 15;
        MethodName methodName = new MethodName(color, stackElements, methodWidth);
        assertCtor(null, null, 15, methodName);
    }    
}
