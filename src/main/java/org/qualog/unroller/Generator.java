package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for various object types.
 */
public class Generator {
    private final ObjectGenerator objects;

    public Generator(StringGenerator strings, Integer limit) {
        objects = new ObjectGenerator(strings, limit);
    }

    public Generator(StringGenerator strings) {
        this(strings, null);
    }
    
    public void format(String key, Object value) {
        objects.format(key, value);
    }
}
