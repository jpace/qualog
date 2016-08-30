package org.qualog.output;

import java.util.*;

public class FieldMessage extends Message {
    private final String field;
    
    public FieldMessage(ANSIColorList colors, StackElements stackElements, String field, String msg) {
        super(colors, stackElements, msg);
        this.field = field;
    }

    public Object getValue(StackTraceElement stackElement) {
        return field + ": " + super.getValue(stackElement);
    }
}
