package org.qualog.format;

import org.qualog.unroller.StringFormatter;

public class ContextStringFormatter implements StringFormatter {
    public static final String DEFAULT_FORMAT = "%s - %s";

    private final String contextID;
    private final String lineFormat;
    private final StringFormatter strFormat;

    public ContextStringFormatter(String contextID) {
        this(contextID, DEFAULT_FORMAT, new MessageFormatter());
    }

    public ContextStringFormatter(String contextID, String lineFormat, StringFormatter strFormat) {
        this.contextID = contextID;
        this.lineFormat = lineFormat;
        this.strFormat = strFormat;
    }
    
    public String format(String key, String value) {
        String msgStr = strFormat.format(key, value);
        return String.format(this.lineFormat, contextID, msgStr);
    }
    
    public String format(String msg) {
        String msgStr = strFormat.format(msg);
        return String.format(this.lineFormat, contextID, msgStr);
    }    
}
