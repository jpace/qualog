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
        return location == null ? null : String.format(this.format, location.getFileName(), location.getLineNumber(), location.getClassName(), location.getMethodName());
    }
}
