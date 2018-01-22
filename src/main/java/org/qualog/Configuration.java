package org.qualog;

import org.qualog.config.ColorConfig;
import org.qualog.config.WidthConfig;
import org.qualog.output.ANSIColor;
import org.qualog.output.ANSIColorList;

import static org.incava.ijdk.lang.ICore.or;

/**
 * System-wide settings for logging.
 */
public class Configuration {
    // public static final Configuration WIDE = new Configuration(WidthConfig.WIDE);
    // public static final Configuration MEDIUM = new Configuration(WidthConfig.WIDE);
    // public static final Configuration NARROW = new Configuration(WidthConfig.WIDE);
    // public static final Configuration DEFAULT = MEDIUM;
    
    private boolean showFiles;
    private boolean showClasses;
    private boolean useColumns;

    private final ColorConfig colorConfig;
    private final WidthConfig widthConfig;

    public Configuration() {
        this(new ColorConfig(), new WidthConfig(), true, true, true);
    }

    public Configuration(ColorConfig cc, WidthConfig wc, boolean showFiles, boolean showClasses, boolean useColumns) {
        this.colorConfig = cc;
        this.widthConfig = wc;
        this.showFiles = showFiles;
        this.showClasses = showClasses;
        this.useColumns = useColumns;
    }

    public ColorConfig getColorConfig() {
        return colorConfig;
    }

    public WidthConfig getWidthConfig() {
        return widthConfig;
    }

    public ANSIColor getColor(StackTraceElement ste) {
        return or(colorConfig.getMethodColor(ste.getClassName(), ste.getMethodName()),
                  colorConfig.getClassColor(ste.getClassName()),
                  colorConfig.getFileColor(ste.getFileName()));
    }

    public boolean showFiles() {
        return showFiles;
    }

    public void setShowFiles(boolean showFiles) {
        this.showFiles = showFiles;
    }

    public boolean showClasses() {
        return showClasses;
    }

    public void setShowClasses(boolean showClasses) {
        this.showClasses = showClasses;
    }

    public boolean useColumns() {
        return useColumns;
    }

    public void setUseColumns(boolean useColumns) {
        this.useColumns = useColumns;
    }
}
