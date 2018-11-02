package org.qualog.writer;

import java.io.PrintWriter;
import org.qualog.format.LineFormatter;
import org.qualog.format.Location;

public class LineWriter implements StringWriter {
    private final LineFormatter lineFormatter;
    private final PrintWriter printWriter;
    
    private String contextId;
    private Location location;
    
    public LineWriter(String contextId, LineFormatter lineFormatter, PrintWriter printWriter) {
        this.contextId = contextId;
        this.location = null;
        this.lineFormatter = lineFormatter;
        this.printWriter = printWriter;
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
        this.printWriter.println(str);
    }
}
