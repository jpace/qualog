package org.qualog.format;

public class ContextStringFormatter {
    public static final String DEFAULT_FORMAT = "%s";

    private final String format;

    public ContextStringFormatter() {
        this(DEFAULT_FORMAT);
    }

    public ContextStringFormatter(String format) {
        this.format = format;
    }
    
    public String format(String contextId) {
        return String.format(format, contextId);
    }
}
