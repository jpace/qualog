package org.qualog.writer;

import org.qualog.format.MessageFormatter;
import org.qualog.format.StringFormatter;
import org.qualog.unroller.Generator;
import org.qualog.unroller.StringGenerator;
import org.qualog.util.Stack;

public class LogWriter {
    private final Generator generator;

    public LogWriter(StringFormatter strFmt, StringWriter writer) {
        this(new Generator(new StringGenerator(strFmt, writer)));
    }

    public LogWriter(Generator generator) {
        this.generator = generator;
    }

    public boolean stack(String key, Object value) {
        generator.generate(key, value);
        return true;
    }

    public boolean log(String key, Object value) {
        //$$$ get the top-most frame
        //$$$ format the location (whence)
        //$$$ generate
        Stack stack = new Stack();
        // skip all the org.qualog ones ...
        for (StackTraceElement it : stack.getElements()) {
            System.out.println("it: " + it);
        }

        Statement stmt = new Statement(stack, "abc", key, value);
        return log(stmt);
    }

    public boolean log(Statement stmt) {
        //$$$ get the top-most frame
        //$$$ format the location (whence)
        //$$$ generate
        Stack stack = new Stack();
        // skip all the org.qualog ones ...
        for (StackTraceElement it : stack.getElements()) {
            System.out.println("it: " + it);
        }

        generator.generate(stmt);
        return true;
    }
}
