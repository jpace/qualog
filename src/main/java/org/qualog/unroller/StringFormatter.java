package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for strings.
 */
public class StringFormatter {
    public static final String DEFAULT_FORMAT = "%s: %s";
    
    private final String format;
    private final StringArray lines;

    public StringFormatter(StringArray lines) {
        this(DEFAULT_FORMAT, lines);
    }

    public StringFormatter(String format, StringArray lines) {
        this.format = format == null ? DEFAULT_FORMAT : format;
        this.lines = lines;
    }
    
    public void format(String key, String value) {
        String line = String.format(this.format, key, value);
        lines.add(line);
    }
    
    public void format(String msg) {
        lines.add(msg == null ? "null" : msg);
    }

    public void formatNull(String key) {
        format(key, "null");
    }    
}
