package org.qualog.writer;

import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.StringArray;
import org.qualog.format.ContextStringFormatter;
import org.qualog.format.Location;
import org.qualog.format.LocationFormatter;
import org.qualog.format.MessageFormatter;
import org.qualog.format.StringFormatter;
import org.qualog.output.StdOut;
import org.qualog.unroller.Generator;
import org.qualog.unroller.StringGenerator;
import org.qualog.util.Stack;

public class LogWriter {
    private final Generator generator;
    private StringArray lines;
    private StdOut out;
    private final LocationFormatter locationFormatter;
    private final ContextStringFormatter contextFormatter;

    public LogWriter(StringFormatter strFmt, StringWriter writer) {
        this(new Generator(new StringGenerator(strFmt, writer)));
    }

    public LogWriter(Generator generator) {
        this.generator = generator;
        this.locationFormatter = new LocationFormatter("[%-20s: %5d] {%-20s#%15s}");
        this.contextFormatter = new ContextStringFormatter("-20s");
    }

    public LogWriter() {
        this.lines = StringArray.empty();
        StringArrayWriter writer = new StringArrayWriter(this.lines);
        this.generator = new Generator(new StringGenerator(new MessageFormatter("%-20s => %s", "%s"), writer), null);
        this.out = new StdOut();
        this.locationFormatter = new LocationFormatter("[%-20s: %5d] {%-20s#%20s}");
        this.contextFormatter = new ContextStringFormatter("%-20s");
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

        Statement stmt = new Statement(stack, "ctx-1", key, value);
        return log(stmt);
    }

    public boolean log(Statement stmt) {
        //$$$ get the top-most frame
        //$$$ format the location (whence)
        //$$$ generate
        Stack stack = new Stack();
        // skip all the org.qualog ones ...
        Array<StackTraceElement> elements = stack.getElements();
        StackTraceElement whence = null;
        for (StackTraceElement it : elements) {
            String clsName = it.getClassName();
            if (!clsName.startsWith("org.qualog") || clsName.endsWith("Test")) {
                whence = it;
                break;
            }
        }

        Location location = new Location(whence.getFileName(), whence.getLineNumber(), whence.getClassName(), whence.getMethodName());
        String locStr = this.locationFormatter.format(location);
        
        this.lines.clear();
        
        generator.generate(stmt);

        String ctxId = "ctx-123";
        String ctxStr = contextFormatter.format(ctxId);

        for (String line : lines) {
            String str = ctxStr + " - " + locStr + " - " + line;
            this.out.println(str);
        }

        this.lines.clear();
        
        return true;
    }
}
