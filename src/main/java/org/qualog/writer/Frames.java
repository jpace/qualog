package org.qualog.writer;

import org.qualog.format.Fields;
import org.qualog.format.Location;

public class Frames {
    private final StackTraceElement previous;
    private final StackTraceElement current;
    private final Fields fields;

    public Frames(StackTraceElement previous, StackTraceElement current, Fields fields) {
        this.previous = previous;
        this.current = current;
        this.fields = fields;
    }
    
    public Location getLocation() {
        return new Location(toText(previous.getFileName(), current.getFileName(), fields.getRepeatFileName()),
                            current.getLineNumber(),
                            toText(previous.getClassName(), current.getClassName(), fields.getRepeatClassName()),
                            toText(previous.getMethodName(), current.getMethodName(), fields.getRepeatMethodName()));
    }

    private String toText(String prevValue, String currValue, String replacement) {
        return prevValue.equals(currValue) ? replacement :currValue;
    }
}
