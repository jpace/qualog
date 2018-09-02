package org.qualog.output;

import org.incava.ijdk.lang.Str;

public class ClassName extends Item {    
    public ClassName(ANSIColor color, StackElements stackElements, Integer classWidth) {
        super(color, stackElements, classWidth);
    }

    public Object getValue(StackTraceElement stackElement) {
        if (isRepeated()) {
            return new Str(" ", width).str();
        }

        String className = stackElement.getClassName();        
        String concise = asConcise(className);
        return getSnipped(concise);
    }

    public String getStackField(StackTraceElement stackElement) {
        return stackElement.getClassName();
    }

    protected String asConcise(String className) {
        // this used to be "(com|org)\\.\\w+\\.", "...", replacing only the top domain.
        return className.replaceAll("(\\w)\\w+\\.", "$1.");
    }
}
