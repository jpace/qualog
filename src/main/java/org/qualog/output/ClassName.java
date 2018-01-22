package org.qualog.output;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.incava.ijdk.lang.Str;
import org.incava.ijdk.lang.StringExt;

public class ClassName extends Item {    
    public ClassName(ANSIColor color, StackElements stackElements, int classWidth) {
        super(color, stackElements, classWidth);
    }

    public Object getValue(StackTraceElement stackElement) {
        if (isRepeated()) {
            return new Str(" ", width).str();
        }

        String className = stackElement.getClassName();        
        className = asConcise(className);
        return getSnipped(className);
    }

    public String getStackField(StackTraceElement stackElement) {
        return stackElement.getClassName();
    }

    protected String asConcise(String className) {
        // this used to be "(com|org)\\.\\w+\\.", "...", replacing only the top domain.
        return className.replaceAll("(\\w)\\w+\\.", "$1.");
    }
}
