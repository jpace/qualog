package org.qualog.unroller;

public class ContextStringFormatter extends StringFormatter {
    public static final String DEFAULT_LINE_FORMAT = "%s %s: %s";

    private final String contextID;
    private final String lineFormat;

    public ContextStringFormatter(String contextID) {
        this(DEFAULT_FORMAT, contextID);
    }

    public ContextStringFormatter(String msgFormat, String contextID) {
        super(msgFormat);

        this.lineFormat = "%s - " + msgFormat;
        this.contextID = contextID;        
    }
    
    public String format(String key, String value) {
        return String.format(this.lineFormat, this.contextID, key, value);
    }
    
    public String format(String msg) {
        return this.contextID + " - " + msg;
    }
}
