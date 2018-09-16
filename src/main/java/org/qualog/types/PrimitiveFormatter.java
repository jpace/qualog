package org.qualog.types;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for primitives and arrays of primitives (which do not expand
 * recursively).
 */
public class PrimitiveFormatter {
    private final StringFormatter strings;
    
    public PrimitiveFormatter(String format, StringArray lines) {
        strings = new StringFormatter(format, lines);
    }
    
    public PrimitiveFormatter(StringArray lines) {
        this(StringFormatter.DEFAULT_FORMAT, lines);
    }
    
    public void format(String key, String value) {
        strings.format(key, value);
    }
    
    public void format(String key, boolean x) {
        format(key, String.valueOf(x));
    }

    public void format(String key, byte x) {
        format(key, String.valueOf(x));
    }

    public void format(String key, char x) {
        format(key, String.valueOf(x));
    }

    public void format(String key, double x) {
        format(key, String.valueOf(x));
    }
 
    public void format(String key, float x) {
        format(key, String.valueOf(x));
    }

    public void format(String key, int x) {
        format(key, String.valueOf(x));
    }

    public void format(String key, long x) {
        format(key, String.valueOf(x));
    }

    public void format(String key, short x) {
        format(key, String.valueOf(x));
    }    
    
    public void format(String key, Object idx, boolean x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, byte x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, char x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, double x) {
        format(key, idx, String.valueOf(x));
    }
 
    public void format(String key, Object idx, float x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, int x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, long x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, short x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, String value) {
        format(key + "[" + idx + "]", value);
    }    
}
