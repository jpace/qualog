package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates one line, using the specified or default format.
 */
public class StringFormatter {
    public static final String DEFAULT_FORMAT = "%s: %s";
    
    private final String format;

    public StringFormatter() {
        this(DEFAULT_FORMAT);
    }

    public StringFormatter(String format) {
        this.format = format == null ? DEFAULT_FORMAT : format;
    }
    
    public String format(String key, String value) {
        return String.format(this.format, key, value);
    }
    
    public String format(String msg) {
        return msg == null ? "null" : msg;
    }

    public String formatNull(String key) {
        return format(key, "null");
    }
}
