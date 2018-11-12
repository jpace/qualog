package org.qualog.output;

import java.io.PrintWriter;
import org.qualog.ClassFilter;
import org.qualog.Filter;
import org.qualog.Level;
import org.qualog.Log;
import org.qualog.config.ColorConfig;
import org.qualog.config.Configuration;
import org.qualog.types.LogElement;
import org.qualog.types.LogElementFactory;

/**
 * <p>Writes the logging output, applying filters and decorations. The <code>Log</code> and
 * <code>Logger</code> classes offers cleaner and more thorough interfaces than this class.</p>
 *
 * @see org.qualog.Log
 * @see org.qualog.Logger
 */
public class Writer {
    private Configuration config;

    private PrintWriter out;

    private final OmittedFilter omitted;
    
    private OutputType outputType;
    private StackTraceElement prevStackElement;
    private Thread prevThread;
    private Level level;
    private FilterList filters;
    
    public Writer() {
        this(new Configuration());
    }

    public Writer(Configuration config) {
        this.config = config;
        
        // this writes to stdout even in Gradle and Ant, which redirect stdout:
        this.out = new StdOut();

        this.omitted = new OmittedFilter();
    
        this.outputType = OutputType.NONE;
        this.prevStackElement = null;    
        this.prevThread = null;
        this.level = new Level(9);
        this.filters = new FilterList();
    }

    /**
     * Adds a filter to be applied for output.
     *
     * @see org.qualog.Filter
     */
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public void setDisabled(Class<?> cls) {
        addFilter(new ClassFilter(cls, null));
    }

    public void setConfiguration(Configuration config) {
        this.config = config;
    }

    public Configuration getConfiguration() {
        return config;
    }

    /**
     * Sets the output type and level. Either verbose or quiet can be enabled.
     */
    public void setOutput(OutputType type, Level level) {
        this.outputType = type;
        this.level = level;
    }

    public boolean verbose() {
        return outputType.equals(OutputType.VERBOSE);
    }

    public void addClassSkipped(Class<?> cls) {
        omitted.addClassSkipped(cls);
    }
    
    public void addClassSkipped(String clsName) {
        omitted.addClassSkipped(clsName);
    }

    public void addPackageSkipped(String pkgName) {
        omitted.addPackageSkipped(pkgName);
    }

    /**
     * Sets parameters to their defaults.
     */
    public void clear() {
        this.config = new Configuration();
        
        this.prevStackElement = null;
        this.prevThread = null;
        this.level = new Level(9);
        this.filters = new FilterList();
    }

    /**
     * Resets the thread and stack element.
     */
    public void reset() {
        this.prevThread = Thread.currentThread();
        this.prevStackElement = null;
    }

    public boolean stack(Level level, ItemColors colors, String name, Object obj, int numFrames) {
        if (!isLoggable(level)) {
            return true;
        }

        LogElement le = LogElementFactory.create(level, colors, name, obj, numFrames);
        return le.stack(this);
    }

    public boolean isLoggable(Level lvl) {
        return level.isLoggable(outputType, lvl);
    }

    private int getNumFrames(LogElement logElmt) {
        // only show 1 frame in quiet mode:
        return outputType.equals(OutputType.QUIET) ? 1 : logElmt.getNumFrames();
    }

    /**
     * Logs the element. Assumes the level is already matched.
     */
    public synchronized boolean stack(LogElement logElmt) {
        // when we're switching threads, reset to a null state.
        if (!Thread.currentThread().equals(prevThread)) {
            reset();
        }

        int numFrames = getNumFrames(logElmt);
        
        StackTraceElement[] stack = getStack(numFrames);
        
        int frameIdx = findStackStart(stack);
        
        for (int framesShown = 0; frameIdx < stack.length && framesShown < numFrames; ++frameIdx, ++framesShown) {
            StackTraceElement stackElement = stack[frameIdx];

            if (framesShown == 0 && !filters.isLoggable(stackElement, level)) {
                break;
            }

            ItemColors lineColors = getLineColors(logElmt, stackElement);
            
            Line line = new Line(logElmt.getMessage(), lineColors, stackElement, prevStackElement, config);
            boolean isRepeated = framesShown > 0;
            boolean verboseOutput = outputType.equals(OutputType.VERBOSE);
            out.println(line.getLine(isRepeated, verboseOutput));
            prevStackElement = stackElement;
        }
        return true;
    }

    private ItemColors getLineColors(LogElement logElmt, StackTraceElement stackElement) {
        Colors colors = new Colors(config.getColorConfig());
        ItemColors elmtColors = logElmt.getColors();
        return colors.getLineColors(elmtColors, stackElement);
    }
    
    private static StackTraceElement[] getStack(int depth) {
        return (new Exception("")).getStackTrace();
    }

    /**
     * Returns the index in the stack where logging (stacks) should be displayed. Returns -1 if the
     * end of the stack is reached and no logging should occur.
     */
    public synchronized int findStackStart(StackTraceElement[] stack) {
        for (int fi = 0; fi < stack.length; ++fi) {
            if (!omitted.isSkipped(stack[fi])) {
                return fi;
            }
        }
        return stack.length;
    }
}
