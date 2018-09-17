package org.qualog.unroller;

import org.incava.attest.Parameterized;

public class LineTimestampStringFormatter extends TimestampStringFormatter {
    private String line;

    public LineTimestampStringFormatter() {
    }

    public LineTimestampStringFormatter(String format) {
        super(format);
    }

    public void write(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }
}
