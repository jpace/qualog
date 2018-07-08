package org.qualog.output;

import org.incava.ijdk.lang.Objects;
import org.incava.ijdk.lang.Str;

public class MethodName extends Item {    
    public MethodName(ANSIColor color, StackElements stackElements, int methodWidth) {
        super(color, stackElements, methodWidth);
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

    public boolean isRepeated(StackElements stackElements) {
        StackTraceElement previous = stackElements.getPrevious();
        if (previous == null) {
            return false;
        }

        StackTraceElement current = stackElements.getCurrent();
        return (Objects.equal(previous.getMethodName(), current.getMethodName()) &&
                Objects.equal(previous.getClassName(),  current.getClassName()));
    }
    
    public String getStackField(StackTraceElement stackElement) {
        return stackElement.getMethodName();
    }
}
