package org.qualog.output;

import ijdk.lang.Str;
import junit.framework.TestCase;

public class TestMethodName extends TestCase {
    public TestMethodName(String name) {
        super(name);
    }

    private StackTraceElement ste(String className, String methodName, String fileName, int lineNumber) {
        return new StackTraceElement(className, methodName, fileName, lineNumber);
    }

    private StackElements stes(StackTraceElement current, StackTraceElement previous) {
        return  new StackElements(current, previous);
    }

    private MethodName methodName(StackElements stackElements) {
        return methodName(null, stackElements, 15);
    }

    private MethodName methodName(StackTraceElement current, StackTraceElement previous) {
        return methodName(new StackElements(current, previous));
    }

    private MethodName methodName(ANSIColor color, StackElements stackElements, int methodWidth) {
        return new MethodName(color, stackElements, methodWidth);
    }

    // ctor

    private MethodName assertCtor(ANSIColor expectedColor,
                                  StackElements expectedStackElements,
                                  int expectedMethodWidth,
                                  MethodName methodName) {
        String msg = "methodName: " + methodName;
        assertEquals(msg, true, methodName.snipIfLong());
        assertEquals(msg, true, methodName.justifyLeft());
        assertEquals(msg, "TestShellComma-", methodName.getSnipped("TestShellCommand"));
        assertEquals(msg, expectedMethodWidth, methodName.getWidth());
        return methodName;
    }

    public void testCtorDefault() {
        MethodName methodName = methodName(null, null, 15);
        assertCtor(null, null, 15, methodName);
    }

    // getValue

    private Object assertGetValue(String expected, MethodName methodName) {
        Object result = methodName.getValue();
        assertEquals("methodName: " + methodName, expected, result);
        return result;
    }

    public void testGetValueDefault() {
        assertGetValue("c", methodName(ste("a.B", "c", "B.java", 1), null));
    }

    public void testGetValueRepeated() {
        StackElements stackElements = stes(ste("a.B", "c", "B.java", 1),
                                           ste("a.B", "c", "B.java", 4));
        int methodWidth = 15;
        String value = new Str(" ").repeat(methodWidth);
        assertGetValue(value, methodName(null, stackElements, methodWidth));
    }

    public void testGetValueSnipped() {
        MethodName methodName = methodName(null, stes(ste("a.B", "method6789012345", "B.java", 1), null), 15);
        assertGetValue("method67890123-", methodName);
    }

    // isRepeated

    private boolean assertIsRepeated(boolean expected, MethodName methodName) {
        boolean result = methodName.isRepeated();
        assertEquals("methodName: " + methodName, expected, result);
        return result;
    }

    public void testIsRepeatedNullPrevious() {
        assertIsRepeated(false, methodName(ste("a.B", "c", "B.java", 1), null));
    }

    public void testIsRepeatedSameClassAndMethod() {
        assertIsRepeated(true, methodName(ste("a.B", "c", "B.java", 1),
                                          ste("a.B", "c", "B.java", 4)));
    }

    public void testIsRepeatedSameClassDifferentMethod() {
        assertIsRepeated(false, methodName(ste("a.B", "d", "B.java", 1),
                                           ste("a.B", "c", "B.java", 1)));
    }

    public void testIsRepeatedDifferentClassSameMethod() {
        assertIsRepeated(false, methodName(ste("a.C", "c", "B.java", 1),
                                           ste("a.B", "c", "B.java", 4)));
    }
}
