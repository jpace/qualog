package org.qualog.output;

import org.incava.ijdk.lang.Str;

public class ClassName extends Item {
    private final Integer width;
    
    public ClassName(ANSIColor color, StackElements stackElements, Integer width) {
        super(color, stackElements, width);

        this.width = width;
    }

    public Object getValue(StackTraceElement stackElement) {
        if (isRepeated()) {
            return new Str(' ', this.width).str();
        }

        String className = stackElement.getClassName();        
        String concise = asConcise(className);
        return getSnipped(concise);
    }

    public String getStackField(StackTraceElement stackElement) {
        return stackElement.getClassName();
    }

    protected String asConcise(String className) {
        return className.replaceAll("(\\w)\\w+\\.", "$1.");
    }
}
