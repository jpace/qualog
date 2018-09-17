package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for various object types.
 */
public class Formatter {
    private final ObjectFormatter objects;

    public Formatter(StringGenerator strings, Integer limit) {
        objects = new ObjectFormatter(strings, limit);
    }

    public Formatter(StringGenerator strings) {
        this(strings, null);
    }
    
    public void format(String key, Object value) {
        objects.format(key, value);
    }
}
