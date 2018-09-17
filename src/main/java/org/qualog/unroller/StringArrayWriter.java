package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;

/**
 * Adds output strings to a StringArray.
 */
public class StringArrayWriter implements StringWriter {
    private final StringArray lines;

    public StringArrayWriter(StringArray lines) {
        this.lines = lines;
    }

    public void write(String line) {
        lines.add(line);
    }
}
