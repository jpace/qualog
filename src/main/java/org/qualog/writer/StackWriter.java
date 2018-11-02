package org.qualog.writer;

import org.incava.ijdk.collect.Array;
import org.incava.ijdk.lang.KeyValue;
import org.qualog.format.Fields;
import org.qualog.format.Location;
import org.qualog.unroller.Generator;

public class StackWriter {
    private final Generator generator;
    private final LineWriter lineWriter;
    private final Fields fields;

    public StackWriter(Generator generator, LineWriter lineWriter, Fields fields) {
        this.generator = generator;
        this.lineWriter = lineWriter;
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

    public boolean write(Statement stmt, int numFrames, StackTraceElement previous) {
        Array<StackTraceElement> elements = stmt.getStack().getElements();
        KeyValue<Integer, StackTraceElement> current = stmt.getWhenceFrame();

        Integer currentIdx = current.key();
        StackTraceElement currentFrame = current.value();
        
        Location location = getLocation(previous, currentFrame);
        
        lineWriter.setLocation(location);
        
        generator.generate(stmt);

        for (StackTraceElement frame : elements.get(currentIdx + 1, currentIdx + numFrames)) {
            Location loc = new Location(frame);
            lineWriter.write(loc, fields.getStackMessage());
        }
        
        return true;
    }    
}
