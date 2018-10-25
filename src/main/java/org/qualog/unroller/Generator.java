package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;
import org.qualog.format.MessageFormatter;
import org.qualog.writer.Statement;
import org.qualog.writer.StringArrayWriter;

/**
 * Generates lists of lines for various object types.
 */
public class Generator {
    private final ObjectGenerator objects;
    private StringArray lines;

    public Generator(StringGenerator strings, Integer limit) {
        this.objects = new ObjectGenerator(strings, limit);
    }

    public Generator(StringGenerator strings) {
        this(strings, null);
    }

    public Generator() {
        this.lines = StringArray.empty();
        StringArrayWriter writer = new StringArrayWriter(this.lines);
        this.objects = new ObjectGenerator(new StringGenerator(new MessageFormatter(), writer), null);
    }
    
    public void generate(String key, Object value) {
        objects.generate(key, value);
    }
    
    public void generate(Statement stmt) {
        objects.generate(stmt.getKey(), stmt.getValue());
    }
}
