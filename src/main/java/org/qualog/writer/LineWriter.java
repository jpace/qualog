package org.qualog.writer;

import org.qualog.format.ContextIdFormatter;
import org.qualog.format.LineFormatter;
import org.qualog.format.Location;
import org.qualog.format.LocationFormatter;
import org.qualog.output.StdOut;

public class LineWriter implements StringWriter {
    private final LineFormatter lineFormatter;
    
    private String contextId;
    private Location location;
    private StdOut out;
    
    public LineWriter(String contextId, ContextIdFormatter contextIdFormatter, LocationFormatter locationFormatter) {
        this(contextId, new LineFormatter(contextIdFormatter, locationFormatter));
    }

    public LineWriter(String contextId, LineFormatter lineFormatter) {
        this.contextId = contextId;
        this.location = null;
        this.lineFormatter = lineFormatter;
        this.out = new StdOut();
    }

    public LineWriter(String contextId) {
        this(contextId, new ContextIdFormatter("%-20s"), new LocationFormatter("%-20.20s %5d | %-25.25s %-15.15s"));
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public void write(String line) {
        write(location, line);
    }

    public void write(Location location, String line) {
        Line ln = new Line(contextId, location, line);
        String str = ln.format(this.lineFormatter);
        this.out.println(str);
    }
}
