package org.qualog.types;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for containers (fixed-size arrays and collections).
 */
public class ContainerFormatter {
    private final Integer limit;
    private final StringFormatter strings;
    
    public ContainerFormatter(StringArray lines, Integer limit) {
        this.strings = new StringFormatter(lines);
        this.limit = limit;
    }

    public ContainerFormatter(StringArray lines) {
        this(lines, null);
    }    
    
    public void formatEmpty(String key) {
        strings.format(key, "()");
    }
    
    public void format(String key, String value) {
        strings.format(key, value);
    }
    
    public void format(String msg) {
        strings.format(msg);
    }
    
    public int getLimit(int size) {
        return this.limit == null ? size : Math.min(size, this.limit);
    }

    public boolean withinLimit(int idx) {
        return this.limit == null || idx < this.limit;
    }

    public boolean checkNull(String key, Object obj) {
        if (obj == null) {
            strings.formatNull(key);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkEmpty(String key, int length) {
        return checkEmpty(key, length == 0);
    }    

    public boolean checkEmpty(String key, boolean condition) {
        if (condition) {
            formatEmpty(key);
            return true;
        }
        else {
            return false;
        }
    }    
}
