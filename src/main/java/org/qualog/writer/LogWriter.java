package org.qualog.writer;

import org.qualog.format.Fields;
import org.qualog.format.Formats;
import org.qualog.unroller.Generator;
import org.qualog.unroller.StringGenerator;
import org.qualog.util.Stack;

public class LogWriter {
    public static final Integer DEFAULT_DEPTH = 5;
    
    private final StackWriter stackWriter;
    
    private StackTraceElement previous;

    public LogWriter(LineWriter lineWriter, Formats formats) {
        this(new Generator(new StringGenerator(formats.message(), lineWriter), null), lineWriter, new Fields());
    }    

    public LogWriter(Generator generator, LineWriter lineWriter, Fields fields) {
        this.previous = null;
        this.stackWriter = new StackWriter(generator, lineWriter, fields);
    }    

    public boolean stack(Integer depth, String key, Object value) {
        return stack(depth, new Statement(new Stack(), "", key, value));
    }

    public boolean stack(Integer depth, String msg) {
        return stack(depth, new Statement(new Stack(), "", msg));
    }

    public boolean stack(Integer depth, Statement stmt) {
        stackWriter.write(stmt, depth, previous);
        previous = stmt.getWhenceFrame().value();
        
        return true;
    }

    public boolean stack(String key, Object value) {
        return stack(DEFAULT_DEPTH, key, value);
    }

    public boolean stack(String msg) {
        return stack(DEFAULT_DEPTH, msg);
    }

    public boolean stack(Statement stmt) {
        return stack(DEFAULT_DEPTH, stmt);
    }

    public boolean log(String key, Object value) {
        Stack stack = new Stack();
        Statement stmt = new Statement(stack, "", key, value);
        return log(stmt);
    }

    public boolean log(String msg) {
        Stack stack = new Stack();
        Statement stmt = new Statement(stack, "", msg);
        return log(stmt);
    }

    public boolean log(Statement stmt) {
        return stack(0, stmt);
    }
}
