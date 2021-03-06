package org.qualog;

import java.util.regex.Pattern;
import org.incava.ijdk.lang.Range;

/**
 * Represents a filter for selective enabling or disabling of logging statements.
 */
public class Filter {
    public static final Pattern NO_PATTERN = null;
    public static final Range NO_RANGE = null;
    
    private final Level level;
    private final Pattern fileNamePattern;
    private final Range lineNumberRange;    
    private final Pattern classNamePattern;
    private final Pattern methodNamePattern;

    public Filter(Level level) {
        this(level, (Pattern)null, null, null, null);
    }

    public Filter(Level level, Pattern fnamePat, Range lnum, Pattern clsName, Pattern methName) {
        this.level = level;

        fileNamePattern = fnamePat;
        lineNumberRange = lnum;
        classNamePattern = clsName;
        methodNamePattern = methName;
    }

    public Filter(Level level, String fname, Range lnum, String clsName, String methName) {
        this(level, toPattern(fname), lnum, toPattern(clsName), toPattern(methName));
    }

    private static Pattern toPattern(String str) {
        return str == null ? (Pattern)null : Pattern.compile(str);
    }

    /**
     * Returns the level.
     *
     * @return the level of this filter
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Returns whether the given parameters match this filter.
     *
     * @param fileName the name of the file
     * @param lineNumber the line number
     * @param className the name of the class
     * @param methodName the name of the method
     * @return whether all fields match
     */
    public boolean isMatch(String fileName, int lineNumber, String className, String methodName) {
        return (isMatch(fileNamePattern,   fileName)   &&
                isMatch(lineNumberRange,   lineNumber) &&
                isMatch(classNamePattern,  className)  && 
                isMatch(methodNamePattern, methodName));
    }

    private boolean isMatch(Pattern pattern, String name) {
        return pattern == null || pattern.matcher(name).matches();
    }

    private boolean isMatch(Range range, int num) {
        return range == null || range.includes(num);
    }

    public boolean isMatch(StackTraceElement ste) {
        return isMatch(ste.getFileName(), ste.getLineNumber(), ste.getClassName(), ste.getMethodName());
    }
}
