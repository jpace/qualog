package org.qualog.writer;

import org.incava.ijdk.collect.Array;
import org.incava.ijdk.lang.KeyValue;
import org.qualog.format.Fields;
import org.qualog.format.Formats;
import org.qualog.format.Location;
import org.qualog.output.StdOut;
import org.qualog.unroller.Generator;
import org.qualog.unroller.StringGenerator;
import org.qualog.util.Stack;

public class LogWriter {
    private final Generator generator;
    private final LineWriter lineWriter;
    private final StackWriter stackWriter;
    private final Fields fields;
    
    private Statement previousStatement;

    public LogWriter() {
        this(new Formats());
    }    

    public LogWriter(Formats formats) {
        this.lineWriter = new LineWriter("", formats.line(), new StdOut());
        this.generator = new Generator(new StringGenerator(formats.message(), lineWriter), null);
        this.previousStatement = null;
        this.fields = new Fields();
        this.stackWriter = new StackWriter(fields);
    }    

    public boolean stack(String key, Object value) {
        return stack(new Statement(new Stack(), "", key, value));
    }

    public boolean stack(Statement stmt) {
        return stack(stmt, 5);
    }

    public boolean stack(Statement stmt, int numFrames) {
        Array<StackTraceElement> elements = stmt.getStack().getElements();
        KeyValue<Integer, StackTraceElement> current = stmt.getWhenceFrame();

        Integer currentIdx = current.key();
        StackTraceElement currentFrame = current.value();
        
        Location location = stackWriter.getLocation(getPreviousElement(), currentFrame);
        
        this.lineWriter.setLocation(location);
        
        generator.generate(stmt);

        for (StackTraceElement frame : elements.get(currentIdx + 1, currentIdx + numFrames)) {
            Location loc = new Location(frame);
            this.lineWriter.write(loc, this.fields.getStackMessage());
        }

        previousStatement = stmt;
        
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

    private StackTraceElement getPreviousElement() {
        return previousStatement == null ? null : previousStatement.getWhenceFrame().value();
    }
}
