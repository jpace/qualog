package org.qualog.config;

public class ConfigFactory {
    public static Configuration createFromProperties() {
        WidthConfig widths      = createWidthConfigFromProperties();        
        boolean     showFiles   = getProperty(Properties.SHOW_FILES,   true);
        boolean     showClasses = getProperty(Properties.SHOW_CLASSES, true);
        boolean     useColumns  = getProperty(Properties.COLUMNAR,     true);
        boolean     isConcise   = getProperty(Properties.CONCISE,      true);
        return create(widths, showFiles, showClasses, useColumns, isConcise);
    }

    public static Configuration create(ConfigType configType) {
        WidthConfig widths = createWidthConfig(configType);
        return create(widths, true, true, true, true);
    }

    public static Configuration create(WidthConfig widths, boolean showFiles, boolean showClasses, boolean useColumns, boolean isConcise) {
        boolean useColor = System.getProperty("os.name").equals("Linux");        
        ColorConfig colors = new ColorConfig(useColor);
        return new Configuration(colors, widths, showFiles, showClasses, useColumns, isConcise);
    }

    public static WidthConfig createWidthConfigFromProperties() {
        Integer fileWidth     = getProperty(Properties.FILE_WIDTH,   WidthConfig.DEFAULT_FILE_WIDTH);
        Integer lineWidth     = getProperty(Properties.LINE_WIDTH,   WidthConfig.DEFAULT_LINE_WIDTH);
        Integer classWidth    = getProperty(Properties.CLASS_WIDTH,  WidthConfig.DEFAULT_CLASS_WIDTH);
        Integer functionWidth = getProperty(Properties.METHOD_WIDTH, WidthConfig.DEFAULT_FUNCTION_WIDTH);

        return new WidthConfig(fileWidth, lineWidth, classWidth, functionWidth);
    }

    public static WidthConfig createWidthConfig(ConfigType configType) {
        switch (configType) {
            case WIDE:
                return WidthConfig.WIDE;
            case MEDIUM:
                return WidthConfig.MEDIUM;
            case DEFAULT:
                return WidthConfig.DEFAULT;
            case NARROW:
                return WidthConfig.NARROW;
            default:
                return WidthConfig.DEFAULT;
        }
    }

    private static Integer getProperty(String name, Integer defValue) {
        Integer value = org.incava.ijdk.util.Properties.getInteger(name);
        return value == null ? defValue : value;
    }

    private static Boolean getProperty(String name, Boolean defValue) {
        Boolean value = org.incava.ijdk.util.Properties.getBoolean(name);
        return value == null ? defValue : value;
    }
}
