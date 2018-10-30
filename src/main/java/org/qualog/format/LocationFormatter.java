package org.qualog.format;

import org.incava.ijdk.collect.StringArray;
import org.incava.ijdk.lang.ICore;

/**
 * Generates a string for a location, using the specified or default format.
 */
public class LocationFormatter {
    public static final String DEFAULT_FORMAT = "[%s %d] {%s#%s}";
    
    private final String format;

    public LocationFormatter() {
        this(DEFAULT_FORMAT);
    }

    public LocationFormatter(String format) {
        this.format = ICore.or(format, DEFAULT_FORMAT);
    }
    
    public String format(Location location) {
        if (location == null) {
            return null;
        }
        String fileName = location.getFileName().replaceFirst(".java", "");
        Integer lineNumber = location.getLineNumber();
        String clsName = getShortClassName(location.getClassName());
        String methodName = location.getMethodName();

        return location == null ? null : String.format(this.format, fileName, lineNumber, clsName, methodName);
    }

    public String getShortClassName(String clsName) {
        String[] comps = clsName.split("\\.");

        if (comps.length > 1) {
            StringBuilder sb = new StringBuilder();
            for (int idx = 0; idx < comps.length - 1; ++idx) {
                sb.append(comps[idx].substring(0, 1)).append('.');
            }
            sb.append(comps[comps.length - 1]);
            return sb.toString();
        }
        else {
            return clsName;
        }
    }
}
