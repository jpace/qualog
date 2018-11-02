package org.qualog.format;

public class Formats {
    public static final ContextIdFormatter CONTEXT_ID = new ContextIdFormatter("%-20s");
    public static final LocationFormatter LOCATION = new LocationFormatter("%-25.25s # %5d | %-25.25s . %-15.15s");
    public static final LineFormatter LINE = new LineFormatter(CONTEXT_ID, LOCATION);
    public static final MessageFormatter MESSAGE = new MessageFormatter("%-25.25s : %s", "%s");
    
    private final ContextIdFormatter contextId;
    private final LocationFormatter location;
    private final LineFormatter line;
    private final MessageFormatter message;

    public Formats(ContextIdFormatter contextId, LocationFormatter location, LineFormatter line, MessageFormatter message) {
        this.contextId = contextId;
        this.location = location;
        this.line = line;
        this.message = message;
    }

    public Formats() {
        this(CONTEXT_ID, LOCATION, LINE, MESSAGE);
    }

    public ContextIdFormatter contextId() {
        return this.contextId;
    }
    
    public LocationFormatter location() {
        return this.location;
    }
    
    public LineFormatter line() {
        return this.line;
    }
    
    public MessageFormatter message() {
        return this.message;
    }    
}
