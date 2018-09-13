package org.qualog.types;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for primitives and arrays of primitives (which do not expand
 * recursively).
 */
public class BaseFormatter {
    private final StringArray lines;

    public BaseFormatter(StringArray lines) {
        this.lines = lines;
    }
    
    public BaseFormatter() {
        this(StringArray.empty());
    }    

    public StringArray getLines() {
        return lines;
    }

    public void format(String key, String value) {
        lines.add(key + ": " + value);
    }
    
    public void format(String msg) {
        lines.add(msg == null ? "null" : msg);
    }

    public void formatNull(String key) {
        format(key, "null");
    }    
}
