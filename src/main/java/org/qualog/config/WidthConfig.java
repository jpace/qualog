package org.qualog.config;

/**
 * Widths of columns in log lines.
 */
public class WidthConfig {
    public static final WidthConfig WIDE = new WidthConfig(25, 5, 35, 25);
    public static final WidthConfig MEDIUM = new WidthConfig(15, 4, 25, 15);
    public static final WidthConfig NARROW = new WidthConfig(10, 4, 20, 10);
    public static final WidthConfig DEFAULT = MEDIUM;

    private int fileWidth = 15;
    private int lineWidth = 4;
    private int classWidth = 25;
    private int functionWidth = 25;

    public WidthConfig() {
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
