package org.qualog.types;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for various object types.
 */
public class Formatter {
    private final ObjectFormatter objects;

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
