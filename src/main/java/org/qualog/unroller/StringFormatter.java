package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lines;
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
    
    public void format(String key, String value) {
        String line = String.format(this.format, key, value);
        write(line);
    }
    
    public void format(String msg) {
        write(msg == null ? "null" : msg);
    }

    public void formatNull(String key) {
        format(key, "null");
    }

    public abstract void write(String line);
}
