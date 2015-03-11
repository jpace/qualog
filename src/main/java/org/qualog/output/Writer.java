package org.qualog.output;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import org.incava.ijdk.lang.*;
import org.qualog.ClassFilter;
import org.qualog.Configuration;
import org.qualog.Filter;
import org.qualog.Level;
import org.qualog.Log;
import org.qualog.config.ColorConfig;
import org.qualog.types.LogElement;
import org.qualog.types.LogElementFactory;
import org.incava.ijdk.util.IUtil;
import static org.incava.ijdk.util.IUtil.*;

/**
 * <p>Writes the logging output, applying filters and decorations. The
 * <code>Log</code> and <code>Logger</code> classes offers cleaner and more
 * thorough interfaces than this class.</p>
 *
 * @see org.qualog.Log
 * @see org.qualog.Logger
 */
public class Writer {
    private Configuration config;

    private PrintWriter out;

    private List<String> packagesSkipped;
    private List<String> classesSkipped;
    private List<String> methodsSkipped;
    
    private OutputType outputType;
    private StackTraceElement prevStackElement;    
    private Thread prevThread;
    private Level level;
    private List<Filter> filters;
    
    public Writer() {
        this(new Configuration());
    }

    public Writer(Configuration config) {
        this.config = config;

        // this writes to stdout even in Gradle and Ant, which redirect stdout:
        this.out = new PrintWriter(new PrintStream(new FileOutputStream(FileDescriptor.out)), true);

        this.packagesSkipped = list("org.qualog", "org.incava.qualog", "org.qualog");
        this.classesSkipped = list("tr.Ace");
        this.methodsSkipped = IUtil.<String>list();
    
        this.outputType = OutputType.NONE;
        this.prevStackElement = null;    
        this.prevThread = null;
        this.level = new Level(9);
        this.filters = IUtil.<Filter>list();
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
        addClassSkipped(cls.getName());
    }
    
    public void addClassSkipped(String clsName) {
        classesSkipped.add(clsName);
    }

    public void addPackageSkipped(String pkgName) {
        packagesSkipped.add(pkgName);
    }

    /**
     * Sets parameters to their defaults.
     */
    public void clear() {
        this.config = new Configuration();
        
        this.prevStackElement = null;
        this.prevThread = null;
        this.level = Log.LEVEL9;
        this.filters = IUtil.<Filter>list();
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

    public boolean isSkipped(StackTraceElement ste) {
        String className = ste.getClassName();
        if (classesSkipped.contains(className) || methodsSkipped.contains(ste.getMethodName())) {
            return true;
        }
        
        for (String pkgName : packagesSkipped) {
            if (className.startsWith(pkgName + ".")) {
                return true;
            }
        }
        return false;
    }

    public boolean isLoggable(Level lvl) {
        return level.isLoggable(outputType, lvl);
    }

    /**
     * Logs the element. Assumes the level is already matched.
     */
    public synchronized boolean stack(LogElement logElmt) {
        // when we're switching threads, reset to a null state.
        if (!Thread.currentThread().equals(prevThread)) {
            reset();
        }

        // only show 1 frame in quiet mode:
        int numFrames = outputType.equals(OutputType.QUIET) ? 1 : logElmt.getNumFrames();
        StackTraceElement[] stack = getStack(numFrames);

        int frameIdx = findStackStart(stack);
        
        for (int framesShown = 0; frameIdx < stack.length && framesShown < numFrames; ++frameIdx, ++framesShown) {
            StackTraceElement stackElement = stack[frameIdx];

            if (framesShown == 0 && !isLoggable(stackElement)) {
                break;
            }

            ItemColors elmtColors = logElmt.getColors();

            // the colors of the message part, not the whole line:
            ANSIColorList msgColors = getMessageColors(elmtColors, stackElement);
            ItemColors lineColors = new ItemColors(msgColors,
                                                   getFileColor(elmtColors, stackElement),
                                                   getClassColor(elmtColors, stackElement),
                                                   getMethodColor(elmtColors, stackElement));
            
            Line line = new Line(logElmt.getMessage(), lineColors, stackElement, prevStackElement, config);
            out.println(line.getLine(framesShown > 0, outputType.equals(OutputType.VERBOSE)));
            prevStackElement = stackElement;
        }
        return true;
    }

    private ANSIColorList getMessageColors(ItemColors elmtColors, StackTraceElement ste) {
        ColorConfig cc = getColorConfig();
        if (!cc.useColor()) {
            return null;
        }

        // the colors of the message part, not the whole line:
        ANSIColorList msgColors = elmtColors.getMessageColors();

        if (isEmpty(msgColors)) {
            ANSIColor col = or(cc.getMethodColor(ste.getClassName(), ste.getMethodName()),
                               cc.getClassColor(ste.getClassName()),
                               cc.getFileColor(ste.getFileName()));

            if (isTrue(col)) {
                msgColors = new ANSIColorList(col);
            }
        }

        return msgColors;
    }

    private boolean isLoggable(StackTraceElement stackElement) {
        boolean isLoggable = true;
        for (Filter filter : filters) {
            Level flevel = filter.getLevel();
            if (filter.isMatch(stackElement)) {
                isLoggable = flevel != null && level.compareTo(flevel) >= 0;
            }
        }
        return isLoggable;
    }

    private static StackTraceElement[] getStack(int depth) {
        return (new Exception("")).getStackTrace();
    }

    /**
     * Returns the index in the stack where logging (stacks) should be
     * displayed. Returns -1 if the end of the stack is reached and no logging
     * should occur.
     */
    public synchronized int findStackStart(StackTraceElement[] stack) {
        for (int fi = 0; fi < stack.length; ++fi) {
            if (!isSkipped(stack[fi])) {
                return fi;
            }
        }
        return stack.length;
    }

    private ANSIColor getFileColor(ItemColors elmtColors, StackTraceElement stackElement) {
        ColorConfig cc = getColorConfig();
        return or(elmtColors.getFileColor(), cc.getFileColor(stackElement.getFileName()));
    }

    private ANSIColor getClassColor(ItemColors elmtColors, StackTraceElement stackElement) {
        ColorConfig cc = getColorConfig();
        return or(elmtColors.getClassColor(), cc.getClassColor(stackElement.getClassName()));
    }

    private ANSIColor getMethodColor(ItemColors elmtColors, StackTraceElement stackElement) {
        ColorConfig cc = getColorConfig();
        return or(elmtColors.getMethodColor(), cc.getMethodColor(stackElement.getClassName(), stackElement.getMethodName()));
    }

    private ColorConfig getColorConfig() {
        return config.getColorConfig();
    }
}
