package org.qualog.config;

import org.incava.ijdk.util.PropertyExt;
import org.qualog.Configuration;
import org.qualog.config.Properties;
import org.qualog.output.ANSIColor;
import org.qualog.output.ANSIColorList;
import static org.incava.ijdk.util.IUtil.*;

public class ConfigFactory {
    // public static final Configuration WIDE = new Configuration(WidthConfig.WIDE);
    // public static final Configuration MEDIUM = new Configuration(WidthConfig.WIDE);
    // public static final Configuration NARROW = new Configuration(WidthConfig.WIDE);
    // public static final Configuration DEFAULT = MEDIUM;

    public static Configuration createFromProperties() {
        Configuration config = new Configuration();
        
        if (System.getProperty("os.name").equals("Linux")) {
            config.getColorConfig().setUseColor(true);
        }

        WidthConfig widths = config.getWidthConfig();
        
        Boolean showFiles = PropertyExt.getBoolean(Properties.SHOW_FILES);
        if (isNotNull(showFiles)) {
            config.setShowFiles(showFiles);
        }

        Boolean showClasses = PropertyExt.getBoolean(Properties.SHOW_CLASSES);
        if (isNotNull(showClasses)) {
            config.setShowClasses(showClasses);
        }

        // Boolean columnar = PropertyExt.getBoolean(Properties.COLUMNAR);
        // if (isNotNull(columnar)) {
        //     writer.setColumns(columnar);
        // }

        Integer fileWidth = PropertyExt.getInteger(Properties.FILE_WIDTH);
        if (isNotNull(fileWidth)) {
            widths.setFileWidth(fileWidth);
        }

        Integer lineWidth = PropertyExt.getInteger(Properties.LINE_WIDTH);
        if (isNotNull(lineWidth)) {
            widths.setLineWidth(lineWidth);
        }

        Integer classWidth = PropertyExt.getInteger(Properties.CLASS_WIDTH);
        if (isNotNull(classWidth)) {
            widths.setClassWidth(classWidth);
        }

        Integer methodWidth = PropertyExt.getInteger(Properties.METHOD_WIDTH);
        if (isNotNull(methodWidth)) {
            widths.setFunctionWidth(methodWidth);
        }

        return config;
    }
}
