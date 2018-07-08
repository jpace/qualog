package org.qualog;

import java.io.PrintWriter;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import org.qualog.config.ConfigFactory;
import org.qualog.config.ConfigType;
import org.qualog.config.Configuration;
import org.qualog.config.MessageFormat;
import org.qualog.config.Properties;
import org.qualog.output.ANSIColor;
import org.qualog.output.ItemColors;
import org.qualog.output.OutputType;
import org.qualog.output.Writer;
import org.qualog.timer.Timer;
import org.qualog.types.LogObject;

/**
 * Base class of the Log class, which expands the interface via generated
 * methods. This class contains the core functionality, at a lower-level.
 *
 * @see Log
 */
public class Logger {
    /**
     * The version of the qualog library.
     */
    public final static String VERSION = "2.1.0";
    
    public static final Level LEVEL0 = new Level(0);
    public static final Level LEVEL1 = new Level(1);
    public static final Level LEVEL2 = new Level(2);
    public static final Level LEVEL3 = new Level(3);
    public static final Level LEVEL4 = new Level(4);
    public static final Level LEVEL5 = new Level(5);
    public static final Level LEVEL6 = new Level(6);
    public static final Level LEVEL7 = new Level(7);
    public static final Level LEVEL8 = new Level(8);
    public static final Level LEVEL9 = new Level(9);

    public static final OutputType NO_OUTPUT = OutputType.NONE;
    public static final OutputType QUIET     = OutputType.QUIET;    
    public static final OutputType VERBOSE   = OutputType.VERBOSE;

    public static final ConfigType WIDE    = ConfigType.WIDE;
    public static final ConfigType MEDIUM  = ConfigType.MEDIUM;
    public static final ConfigType DEFAULT = ConfigType.DEFAULT;
    public static final ConfigType NARROW  = ConfigType.NARROW;
    
    /**
     * The default number of stack trace elements to display in a stack.
     */
    protected static final int DEFAULT_STACK_DEPTH = 5;

    protected static Writer writer;
    protected static Timer timer;

    /**
     * Sets verbose from system property settings.
     */
    protected static void setVerbosity() {
        String verStr = System.getProperty(Properties.VERBOSE, System.getProperty("verbose"));
        if (verStr == null) {
            return;
        }

        boolean verbose = Boolean.valueOf(verStr);
        Level level = LEVEL5;
            
        String lvlStr = System.getProperty(Properties.LEVEL);
        if (lvlStr != null) {
            level = new Level(new Integer(lvlStr));
        }

        if (verbose) {
            setOutput(OutputType.VERBOSE, level);
            System.out.println("Qualog, version " + VERSION);
        }
    }

    static {
        timer = new Timer();
        setVerbosity();
        Configuration config = ConfigFactory.createFromProperties();
        writer = new Writer(config);
    }
    
    public static boolean isLoggable(Level level) {
        return writer.isLoggable(level);
    }

    public static void setDisabled(Class<?> cls) {
        addFilter(new ClassFilter(cls, null));
    }

    public static void addFilter(Filter filter) {
        writer.addFilter(filter);
    }

    public static void setOut(PrintWriter out) {
        writer.setOut(out);
    }

    public static void setClassColor(ANSIColor color) {
        StackTraceElement[] stack = getStack(3);
        String className = stack[2].getClassName();
        // writer.setClassColor(className, color);
    }

    public static void setMethodColor(String methodName, ANSIColor color) {
        StackTraceElement[] stack = getStack(3);
        String className = stack[2].getClassName();
        // writer.setMethodColor(className, methodName, color);
    }

    public static void setFileColor(ANSIColor color) {
        StackTraceElement[] stack = getStack(3);
        String fileName = stack[2].getFileName();
        // writer.setFileColor(fileName, color);
    }

    public static void setConfiguration(Configuration config) {
        writer.setConfiguration(config);
    }

    public static void setConfiguration(ConfigType configType) {
        Configuration config = ConfigFactory.create(configType);
        writer.setConfiguration(config);
    }

    public static Configuration getConfiguration() {
        return writer.getConfiguration();
    }

    public static void setVerbose() {
        setVerbose(true);
    }

    public static void setVerbose(boolean verbose) {
        setOutput(OutputType.VERBOSE, verbose ? LEVEL5 : LEVEL0);
    }

    public static void setFormat(String format) {
        MessageFormat.setFormat(format);
    }

    public static void setQuiet() {
        setQuiet(true);
    }

    public static void setQuiet(boolean quiet) {
        setOutput(OutputType.QUIET, LEVEL5);
    }

    public static void setOutput(OutputType type, Level level) {
        writer.setOutput(type, level);
    }

    public static void setQuiet(Level level) {
        writer.setOutput(OutputType.QUIET, level);
    }

    public static boolean verbose() {
        return writer.verbose();
    }
    
    public static void reset() {
        writer.reset();
    }

    public static void clear() {
        writer.clear();
    }

    public static int findStackStart(StackTraceElement[] stack) {
        return writer.findStackStart(stack);
    }

    public static boolean time(String msg) {
        return timer.start(msg);
    }

    public static boolean time() {
        return timer.start();
    }

    public static boolean start(String msg) {
        return timer.start(msg);
    }

    public static boolean start() {
        return timer.start();
    }

    public static boolean end(String msg) {
        return timer.end(msg);
    }

    public static boolean stack(Level level, EnumSet<ANSIColor> msgColors, String name, Object obj, int numFrames) {
        ItemColors colors = new ItemColors(msgColors);
        return stack(level, colors, name, obj, numFrames);
    }

    public static boolean log(Level level, EnumSet<ANSIColor> msgColors, String name, Object obj) {
        ItemColors colors = new ItemColors(msgColors);
        return stack(level, colors, name, obj, 1);
    }

    public static boolean stack(Level level, ItemColors colors, String name, Object obj, int numFrames) {
        return writer.stack(level, colors, name, obj, numFrames);
    }

    public static boolean inspect(Level level, EnumSet<ANSIColor> msgColors, String name, Object obj, int numFrames) {
        ItemColors colors = new ItemColors(msgColors);
        if (obj == null) {
            return stack(level, colors, name, obj, numFrames);
        }
        else {
            Map<String, Object> objMap = LogObject.inspect(obj);
            return stack(level, colors, name, objMap, numFrames);
        }
    }

    /**
     * Writes an empty log message.
     */
    public static boolean log() {
        return log("");
    }

    /**
     * Writes an empty stack message.
     */
    public static boolean stack() {
        return stack("");
    }

    public static boolean stack(Object obj) {
        return stack(LEVEL5, EnumSet.noneOf(ANSIColor.class), null, obj, DEFAULT_STACK_DEPTH);
    }

    public static boolean log(Object obj) {
        return stack(LEVEL5, EnumSet.noneOf(ANSIColor.class), null, obj, 1);
    }

    private static StackTraceElement[] getStack(int depth) {
        return (new Exception("")).getStackTrace();
    }
}
