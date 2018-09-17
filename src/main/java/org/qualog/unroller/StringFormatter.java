package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates one line, using the specified or default format.
 */
public abstract class StringFormatter {
    public static final String DEFAULT_FORMAT = "%s: %s";
    
    private final String format;

    public StringFormatter() {
        this(DEFAULT_FORMAT);
    }

    public StringFormatter(String format) {
        this.format = format == null ? DEFAULT_FORMAT : format;
    }
    
    public String format(String key, String value) {
        String line = String.format(this.format, key, value);
        write(line);
        return line;
    }
    
    public String format(String msg) {
        String line = msg == null ? "null" : msg;
        write(line);
        return line;
    }

    public void formatNull(String key) {
        format(key, "null");
    }

    public abstract void write(String line);
}
