package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;
import org.qualog.io.Statement;

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
    
    public void format(Statement stmt) {
        objects.format(stmt.getKey(), stmt.getValue());
    }
}
