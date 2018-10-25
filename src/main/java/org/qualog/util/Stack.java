package org.qualog.util;

import org.incava.ijdk.collect.Array;

public class Stack {
    private final Array<StackTraceElement> elements;
    
    public Stack() {
        // same as Thread.currentThread().getStackTrace() for the current thread:
        StackTraceElement[] stack = new Exception().getStackTrace();
        this.elements = Array.of(stack);
    }

    public Array<StackTraceElement> getElements() {
        return elements;
    }
}
