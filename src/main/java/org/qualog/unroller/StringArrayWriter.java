package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;

/**
 * Adds output strings to a StringArray.
 */
public class StringArrayWriter extends StringFormatter {
    private final StringArray lines;

    public StringArrayWriter(StringArray lines) {
        this(null, lines);
    }

    public StringArrayWriter(String format, StringArray lines) {
        super(format);

        this.lines = lines;
    }

    public void write(String line) {
        lines.add(line);
    }
}
