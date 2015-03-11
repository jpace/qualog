package org.qualog.config;

/**
 * Widths of columns in log lines.
 */
public class WidthConfig {
    public static final int DEFAULT_FILE_WIDTH = 15;
    public static final int DEFAULT_LINE_WIDTH = 4;
    public static final int DEFAULT_CLASS_WIDTH = 25;
    public static final int DEFAULT_FUNCTION_WIDTH = 15;

    public static final WidthConfig WIDE = new WidthConfig(25, 5, 35, 25);
    public static final WidthConfig MEDIUM = new WidthConfig(DEFAULT_FILE_WIDTH, DEFAULT_LINE_WIDTH,
                                                             DEFAULT_CLASS_WIDTH, DEFAULT_FUNCTION_WIDTH);
    
    public static final WidthConfig NARROW = new WidthConfig(10, 4, 15, 15);
    public static final WidthConfig DEFAULT = MEDIUM;

    private int fileWidth;
    private int lineWidth;
    private int classWidth;
    private int functionWidth;

    public WidthConfig() {
        this(DEFAULT_FILE_WIDTH, DEFAULT_LINE_WIDTH,
             DEFAULT_CLASS_WIDTH, DEFAULT_FUNCTION_WIDTH);
    }

    public WidthConfig(int fileWidth, int lineWidth, int classWidth, int functionWidth) {
        this.fileWidth = fileWidth;
        this.lineWidth = lineWidth;
        this.classWidth = classWidth;
        this.functionWidth = functionWidth;
    }

    public int getFileWidth() {
        return fileWidth;
    }

    public void setFileWidth(int fileWidth) {
        this.fileWidth = fileWidth;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public int getFunctionWidth() {
        return functionWidth;
    }

    public void setFunctionWidth(int functionWidth) {
        this.functionWidth = functionWidth;
    }

    public int getClassWidth() {
        return classWidth;
    }

    public void setClassWidth(int classWidth) {
        this.classWidth = classWidth;
    }
}
