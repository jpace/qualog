package org.qualog.output;

import org.incava.ijdk.lang.Objects;
import org.incava.ijdk.lang.Str;

public class MethodName extends Item {
    private final StackElements stackElements;
    
    public MethodName(ANSIColor color, StackElements stackElements, Integer methodWidth) {
        super(color, stackElements, methodWidth);

        this.stackElements = stackElements;
    }

    protected Object getValue(StackTraceElement stackElement) {
        if (isRepeated()) {
            return new Str(" ", width).str();
        }
        else {
            String methodName = stackElement.getMethodName();
            return getSnipped(methodName);
        }
    }

    public boolean isRepeated() {
        StackTraceElement previous = this.stackElements.getPrevious();
        if (previous == null) {
            return false;
        }

        StackTraceElement current = this.stackElements.getCurrent();
        return (Objects.equal(previous.getMethodName(), current.getMethodName()) &&
                Objects.equal(previous.getClassName(),  current.getClassName()));
    }
    
    public String getStackField(StackTraceElement stackElement) {
        return stackElement.getMethodName();
    }
}
