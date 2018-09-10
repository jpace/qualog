package org.qualog.types;

import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.StringArray;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Generates lists of lines for primitives and arrays of primitives (which do not expand
 * recursively).
 */
public class BaseFormatter {
    private final StringArray lines;
    private final Integer limit;

    public BaseFormatter(StringArray lines, Integer limit) {
        this.lines = lines;
        this.limit = limit;
    }

    public BaseFormatter(StringArray lines) {
        this(lines, null);
    }    
    
    public BaseFormatter(Integer limit) {
        this(StringArray.empty(), limit);
    }    

    public StringArray getLines() {
        return lines;
    }

    public void format(String key, String value) {
        lines.add(key + ": " + value);
    }
    
    public void format(String key, Object idx, Object value) {
        format(key + "[" + idx + "]", String.valueOf(value));
    }

    public void addLine(String key, String value) {
        lines.add(key + ": " + value);
    }

    public void addLine(Object value) {
        lines.add(String.valueOf(value));
    }

    public void formatEmpty(String key) {
        format(key, "()");
    }

    public void formatNull(String key) {
        format(key, "null");
    }

    public int getLimit(int size) {
        return this.limit == null ? size : Math.min(size, this.limit);
    }
}
