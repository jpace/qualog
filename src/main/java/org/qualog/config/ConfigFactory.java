package org.qualog.config;

import org.incava.ijdk.util.PropertyExt;
import org.qualog.Configuration;
import org.qualog.config.Properties;
import static org.incava.ijdk.util.IUtil.*;

public class ConfigFactory {
    // public static final Configuration WIDE = new Configuration(WidthConfig.WIDE);
    // public static final Configuration MEDIUM = new Configuration(WidthConfig.WIDE);
    // public static final Configuration NARROW = new Configuration(WidthConfig.WIDE);
    // public static final Configuration DEFAULT = MEDIUM;

    public static Configuration createFromProperties() {
        boolean useColor = System.getProperty("os.name").equals("Linux");

        WidthConfig widths = createWidthConfigFromProperties();
        
        Boolean showFiles = getProperty(Properties.SHOW_FILES, true);
        Boolean showClasses = getProperty(Properties.SHOW_CLASSES, true);        
        Boolean useColumns = getProperty(Properties.COLUMNAR, true);

        ColorConfig colors = new ColorConfig(useColor);

        return new Configuration(colors, widths, showFiles, showClasses, useColumns);
    }

    public static WidthConfig createWidthConfigFromProperties() {
        Integer fileWidth = getProperty(Properties.FILE_WIDTH, WidthConfig.DEFAULT_FILE_WIDTH);
        Integer lineWidth = getProperty(Properties.LINE_WIDTH, WidthConfig.DEFAULT_LINE_WIDTH);
        Integer classWidth = getProperty(Properties.CLASS_WIDTH, WidthConfig.DEFAULT_CLASS_WIDTH);
        Integer functionWidth = getProperty(Properties.METHOD_WIDTH, WidthConfig.DEFAULT_FUNCTION_WIDTH);

        return new WidthConfig(fileWidth, lineWidth, classWidth, functionWidth);
    }

    private static Integer getProperty(String name, Integer defValue) {
        Integer value = PropertyExt.getInteger(name);
        return value == null ? defValue : value;
    }

    private static Boolean getProperty(String name, Boolean defValue) {
        Boolean value = PropertyExt.getBoolean(name);
        return value == null ? defValue : value;
    }
}
