package org.qualog.unroller;

import org.qualog.writer.Statement;

/**
 * Generates lists of lines for various object types.
 */
public class Generator {
    private final ObjectGenerator objects;

    public Generator(StringGenerator strings, Integer limit) {
        this.objects = new ObjectGenerator(strings, limit);
    }

    public Generator(StringGenerator strings) {
        this(strings, null);
    }
    
    public void generate(String key, Object value) {
        objects.generate(key, value);
    }
    
    public void generate(Statement stmt) {
        if (stmt.hasValue()) {
            objects.generate(stmt.getKey(), stmt.getValue());
        }
        else {
            objects.generate(stmt.getKey());
        }
    }
}
