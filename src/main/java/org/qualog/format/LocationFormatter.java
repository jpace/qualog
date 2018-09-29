package org.qualog.format;

import org.incava.ijdk.collect.StringArray;
import org.incava.ijdk.lang.ICore;
import org.qualog.unroller.StringFormatter;

/**
 * Generates one line, using the specified or default format.
 */
public class LocationFormatter {
    public static final String DEFAULT_FORMAT = "%s";
    
    private final String format;

    public LocationFormatter() {
        this(DEFAULT_FORMAT);
    }

    public LocationFormatter(String format) {
        this.format = ICore.or(format, DEFAULT_FORMAT);
    }
    
    public String format(Location location) {
        return null;
    }
}
