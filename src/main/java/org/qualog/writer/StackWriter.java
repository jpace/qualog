package org.qualog.writer;

import org.qualog.format.Fields;
import org.qualog.format.Location;

public class StackWriter {
    private final Fields fields;

    public StackWriter(Fields fields) {
        this.fields = fields;
    }    

    public Location getLocation(StackTraceElement previous, StackTraceElement current) {
        if (previous != null) {            
            Frames frames = new Frames(previous, current, fields);
            return frames.getLocation();
        }
        else {
            return new Location(current);
        }
    }
}
