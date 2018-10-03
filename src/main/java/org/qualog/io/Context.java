package org.qualog.io;

import org.qualog.util.Stack;

public class Context {
    public static final Object NONE = new Object();
    
    private final Stack stack;
    private final String contextID;
    private final String key;
    private final Object value;

    public Context(Stack stack, String contextID, String key, Object value) {
        this.stack = stack;
        this.contextID = contextID;
        this.key = key;
        this.value = value;
    }

    public Context(Stack stack, String contextID, String key) {
        this(stack, contextID, key, NONE);
    }

    public Stack getStack() {
        return stack;
    }

    public String getContextID() {
        return contextID;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public boolean hasValue() {
        return value != NONE;
    }
}
