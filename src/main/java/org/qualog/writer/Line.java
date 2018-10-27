package org.qualog.writer;

import org.qualog.format.LineFormatter;
import org.qualog.format.Location;

public class Line {
    private final String contextId;
    private final Location location;
    private final String message;
    
    public Line(String contextId, Location location, String message) {
        this.contextId = contextId;
        this.location = location;
        this.message = message;
    }
    
    public Line(String contextId, StackTraceElement frame, String message) {
        this(contextId, new Location(frame.getFileName(), frame.getLineNumber(), frame.getClassName(), frame.getMethodName()), message);
    }

    public String getContextId() {
        return contextId;
    }

    public Location getLocation() {
        return location;
    }

    public String format(LineFormatter formatter) {
        return formatter.format(contextId, location, message);
    }
}
