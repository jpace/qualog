package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for various object unroller.
 */
public class Formatter {
    private final ObjectFormatter objects;

    public Formatter(StringFormatter strings, Integer limit) {
        objects = new ObjectFormatter(strings, limit);
    }

    public Formatter(StringFormatter strings) {
        this(strings, null);
    }

    public Formatter(StringArray lines, Integer limit) {
        objects = new ObjectFormatter(lines, limit);
    }

    public Formatter(StringArray lines) {
        this(lines, null);
    }
    
    public void format(String key, Object value) {
        objects.format(key, value);
    }
}
