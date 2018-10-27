package org.qualog.format;

public class ContextIdFormatter {
    public static final String DEFAULT_FORMAT = "%s";

    private final String format;

    public ContextIdFormatter() {
        this(DEFAULT_FORMAT);
    }

    public ContextIdFormatter(String format) {
        this.format = format;
    }
    
    public String format(String contextId) {
        return String.format(format, contextId);
    }
}
