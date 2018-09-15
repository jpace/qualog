package org.qualog.types;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for strings.
 */
public class StringFormatter {
    private final StringArray lines;

    public StringFormatter(StringArray lines) {
        this.lines = lines;
    }
    
    public void format(String key, String value) {
        lines.add(key + ": " + value);
    }
    
    public void format(String msg) {
        lines.add(msg == null ? "null" : msg);
    }

    public void formatNull(String key) {
        format(key, "null");
    }    
}
