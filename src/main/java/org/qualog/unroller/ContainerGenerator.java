package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for containers (fixed-size arrays and collections).
 */
public class ContainerGenerator {
    private final Integer limit;
    private final StringGenerator strings;

    public ContainerGenerator(StringGenerator strings, Integer limit) {
        this.strings = strings;
        this.limit = limit;
    }    

    public ContainerGenerator(StringGenerator strings) {
        this(strings, null);
    }    
    
    public void generateEmpty(String key) {
        strings.generate(key, "()");
    }
    
    public void generate(String key, String value) {
        strings.generate(key, value);
    }
    
    public void generate(String msg) {
        strings.generate(msg);
    }
    
    public int getLimit(int size) {
        return this.limit == null ? size : Math.min(size, this.limit);
    }

    public boolean withinLimit(int idx) {
        return this.limit == null || idx < this.limit;
    }

    public boolean checkNull(String key, Object obj) {
        if (obj == null) {
            generate(key, "null");
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
            generateEmpty(key);
            return true;
        }
        else {
            return false;
        }
    }    
}
