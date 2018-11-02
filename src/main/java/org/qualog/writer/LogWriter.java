package org.qualog.writer;

import org.qualog.format.Fields;
import org.qualog.format.Formats;
import org.qualog.unroller.Generator;
import org.qualog.unroller.StringGenerator;
import org.qualog.util.Stack;

public class LogWriter {
    private final StackWriter stackWriter;
    
    private StackTraceElement previous;

    public LogWriter(LineWriter lineWriter, Formats formats) {
        this(new Generator(new StringGenerator(formats.message(), lineWriter), null), lineWriter, new Fields());
    }    

    public LogWriter(Generator generator, LineWriter lineWriter, Fields fields) {
        this.previous = null;
        this.stackWriter = new StackWriter(generator, lineWriter, fields);
    }    

    public boolean stack(String key, Object value) {
        return stack(new Statement(new Stack(), "", key, value));
    }

    public boolean stack(Statement stmt) {
        return stack(stmt, 5);
    }

    public boolean stack(Statement stmt, int numFrames) {
        stackWriter.write(stmt, numFrames, previous);
        previous = stmt.getWhenceFrame().value();
        
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
}
