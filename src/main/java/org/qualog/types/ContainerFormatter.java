package org.qualog.types;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for containers.
 */
public class ContainerFormatter extends BaseFormatter {
    private final Integer limit;
    
    public ContainerFormatter(StringArray lines, Integer limit) {
        super(lines);

        this.limit = limit;
    }

    public ContainerFormatter(StringArray lines) {
        this(lines, null);
    }    
    
    public ContainerFormatter(Integer limit) {
        this(StringArray.empty(), limit);
    }

    public ContainerFormatter() {
        this(StringArray.empty(), null);
    }

    public void formatEmpty(String key) {
        format(key, "()");
    }

    public int getLimit(int size) {
        return this.limit == null ? size : Math.min(size, this.limit);
    }

    public boolean withinLimit(int idx) {
        return this.limit == null || idx < this.limit;
    }

    public boolean checkNull(String key, Object obj) {
        if (obj == null) {
            formatNull(key);
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkEmpty(String key, int length) {
        if (length == 0) {
            formatEmpty(key);
            return false;
        }
        else {
            return true;
        }
    }    

    public boolean checkEmpty(String key, boolean condition) {
        if (condition) {
            formatEmpty(key);
            return false;
        }
        else {
            return true;
        }
    }    
}
