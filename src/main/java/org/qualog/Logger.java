package org.qualog;

import java.io.PrintWriter;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import org.qualog.output.ANSIColor;
import org.qualog.output.ItemColors;
import org.qualog.output.OutputType;
import org.qualog.output.Writer;
import org.qualog.timer.Timer;
import org.qualog.types.LogObject;
import org.incava.ijdk.util.PropertyExt;
import static org.incava.ijdk.util.IUtil.*;

/**
 * Base class of the Log class, which expands the interface via generated
 * methods. This class contains the core functionality, at a lower-level.
 *
 * @see Log
 */
public class Logger {
    /**
     * The version of the log module.
     */
    public final static String VERSION = "1.1.1";
    
    public final static String CLASS_WIDTH_PROPERTY_KEY  = "org.qualog.classwidth";
    public final static String COLUMNAR_PROPERTY_KEY     = "org.qualog.columnar";
    public final static String FILE_WIDTH_PROPERTY_KEY   = "org.qualog.filewidth";
    public final static String LEVEL_PROPERTY_KEY        = "org.qualog.level";
    public final static String LINE_WIDTH_PROPERTY_KEY   = "org.qualog.linewidth";
    public final static String METHOD_WIDTH_PROPERTY_KEY = "org.qualog.methodwidth";
    public final static String SHOW_CLASSES_PROPERTY_KEY = "org.qualog.showclasses";
    public final static String SHOW_FILES_PROPERTY_KEY   = "org.qualog.showfiles";
    public final static String VERBOSE_PROPERTY_KEY      = "org.qualog.verbose";
    
    public final static Level LEVEL0 = new Level(0);
    public final static Level LEVEL1 = new Level(1);
    public final static Level LEVEL2 = new Level(2);
    public final static Level LEVEL3 = new Level(3);
    public final static Level LEVEL4 = new Level(4);
    public final static Level LEVEL5 = new Level(5);
    public final static Level LEVEL6 = new Level(6);
    public final static Level LEVEL7 = new Level(7);
    public final static Level LEVEL8 = new Level(8);
    public final static Level LEVEL9 = new Level(9);

    public static final OutputType NO_OUTPUT = OutputType.NONE;
    public static final OutputType QUIET     = OutputType.QUIET;    
    public static final OutputType VERBOSE   = OutputType.VERBOSE;
    
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
        String verStr = System.getProperty(VERBOSE_PROPERTY_KEY, System.getProperty("verbose"));

        if (isNull(verStr)) {
            return;
        }

        boolean verbose = Boolean.valueOf(verStr);
        Level level = LEVEL5;
            
        String lvlStr = System.getProperty(LEVEL_PROPERTY_KEY);
        if (isNotNull(lvlStr)) {
            level = new Level(new Integer(lvlStr));
        }

        if (verbose) {
            setOutput(OutputType.VERBOSE, level);
            System.out.println("Log, version " + VERSION);
        }
    }

    static {
        writer = new Writer();
        timer = new Timer();

        setVerbosity();
        
        if (System.getProperty("os.name").equals("Linux")) {
            writer.getConfiguration().getColorConfig().setUseColor(true);
        }
        
        Boolean showFiles = PropertyExt.getBoolean(SHOW_FILES_PROPERTY_KEY);
        if (isNotNull(showFiles)) {
            writer.setShowFiles(showFiles);
        }

        Boolean showClasses = PropertyExt.getBoolean(SHOW_CLASSES_PROPERTY_KEY);
        if (isNotNull(showClasses)) {
            writer.setShowClasses(showClasses);
        }

        Boolean columnar = PropertyExt.getBoolean(COLUMNAR_PROPERTY_KEY);
        if (isNotNull(columnar)) {
            writer.setColumns(columnar);
        }

        Integer fileWidth = PropertyExt.getInteger(FILE_WIDTH_PROPERTY_KEY);
        if (isNotNull(fileWidth)) {
            writer.setFileWidth(fileWidth);
        }

        Integer lineWidth = PropertyExt.getInteger(LINE_WIDTH_PROPERTY_KEY);
        if (isNotNull(lineWidth)) {
            writer.setLineWidth(lineWidth);
        }

        Integer classWidth = PropertyExt.getInteger(CLASS_WIDTH_PROPERTY_KEY);
        if (isNotNull(classWidth)) {
            writer.setClassWidth(classWidth);
        }

        Integer methodWidth = PropertyExt.getInteger(METHOD_WIDTH_PROPERTY_KEY);
        if (isNotNull(methodWidth)) {
            writer.setFunctionWidth(methodWidth);
        }
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

    public static void set(boolean columns, int fileWidth, int lineWidth, int classWidth, int funcWidth) {
        writer.set(columns, fileWidth, lineWidth, classWidth, funcWidth);
    }

    public static void setConfiguration(Configuration config) {
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

    public static void setColumns(boolean cols) {
        writer.setColumns(cols);
    }
    
    public static void addClassSkipped(Class<?> cls) {
        writer.addClassSkipped(cls);
    }
    
    public static void addClassSkipped(String clsName) {
        writer.addClassSkipped(clsName);
    }
    
    public static void addPackageSkipped(String pkgName) {
        writer.addPackageSkipped(pkgName);
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
        if (isNull(obj)) {
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

    protected static StackTraceElement[] getStack(int depth) {
        return (new Exception("")).getStackTrace();
    }
}
