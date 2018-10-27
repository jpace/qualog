package org.qualog.writer;

import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.Iterate;
import org.incava.ijdk.lang.KeyValue;
import org.qualog.format.ContextIdFormatter;
import org.qualog.format.Location;
import org.qualog.format.LocationFormatter;
import org.qualog.format.MessageFormatter;
import org.qualog.unroller.Generator;
import org.qualog.unroller.StringGenerator;
import org.qualog.util.Stack;

public class LogWriter {
    private final Generator generator;
    private final LineWriter lineWriter;
    
    private Statement previousStatement;

    public LogWriter() {
        this(new ContextIdFormatter("%-20s"), new LocationFormatter("%-20.20s # %5d | %-25.25s . %-15.15s"), new MessageFormatter("%-25.25s : %s", "%s"));
    }    

    public LogWriter(ContextIdFormatter contextIdFormatter, LocationFormatter locationFormatter, MessageFormatter messageFormatter) {
        this.lineWriter = new LineWriter("ctx7", contextIdFormatter, locationFormatter);
        this.generator = new Generator(new StringGenerator(messageFormatter, lineWriter), null);
        this.previousStatement = null;
    }    

    public boolean stack(String key, Object value) {
        return stack(new Statement(new Stack(), "ctx-1", key, value));
    }

    public boolean stack(Statement stmt) {
        return stack(stmt, 5);
    }

    public boolean stack(Statement stmt, int numFrames) {
        Array<StackTraceElement> elements = stmt.getStack().getElements();
        KeyValue<Integer, StackTraceElement> current = stmt.getWhenceFrame();

        Integer currentIdx = current.key();
        StackTraceElement currentFrame = current.value();

        Location location = null;
        if (false && previousStatement != null) {            
            KeyValue<Integer, StackTraceElement> previous = previousStatement.getWhenceFrame();
            StackTraceElement prevFrame = previous.value();
            StackTraceElement currFrame = current.value();

            location = new Location(toText(prevFrame.getFileName(), currFrame.getFileName()),
                                    currFrame.getLineNumber(),
                                    toText(prevFrame.getClassName(), currFrame.getClassName()),
                                    toText(prevFrame.getMethodName(), currFrame.getMethodName()));
        }
        else {
            location = new Location(currentFrame);
        }
        previousStatement = stmt;
        
        this.lineWriter.setLocation(location);
        
        generator.generate(stmt);

        for (StackTraceElement frame : elements.get(currentIdx + 1, currentIdx + numFrames)) {
            Location loc = new Location(frame);
            this.lineWriter.write(loc, null);
        }
        
        return true;
    }

    public boolean log(String key, Object value) {
        Stack stack = new Stack();
        Statement stmt = new Statement(stack, "", key, value);
        return log(stmt);
    }

    public boolean log(Statement stmt) {
        return stack(stmt, 0);
    }

    private String toText(String previous, String current) {
        return previous.equals(current) ? "\"\"" :current;
    }
}
