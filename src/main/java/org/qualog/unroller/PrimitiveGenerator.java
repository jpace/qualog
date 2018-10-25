package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for primitives and arrays of primitives (which do not expand
 * recursively).
 */
public class PrimitiveGenerator {
    private final StringGenerator strings;
    
    public PrimitiveGenerator(StringGenerator strings) {
        this.strings = strings;
    }
    
    public void generate(String key, String value) {
        strings.generate(key, value);
    }
    
    public void generate(String key, boolean x) {
        generate(key, String.valueOf(x));
    }

    public void generate(String key, byte x) {
        generate(key, String.valueOf(x));
    }

    public void generate(String key, char x) {
        generate(key, String.valueOf(x));
    }

    public void generate(String key, double x) {
        generate(key, String.valueOf(x));
    }
 
    public void generate(String key, float x) {
        generate(key, String.valueOf(x));
    }

    public void generate(String key, int x) {
        generate(key, String.valueOf(x));
    }

    public void generate(String key, long x) {
        generate(key, String.valueOf(x));
    }

    public void generate(String key, short x) {
        generate(key, String.valueOf(x));
    }    
    
    public void generate(String key, Object idx, boolean x) {
        generate(key, idx, String.valueOf(x));
    }

    public void generate(String key, Object idx, byte x) {
        generate(key, idx, String.valueOf(x));
    }

    public void generate(String key, Object idx, char x) {
        generate(key, idx, String.valueOf(x));
    }

    public void generate(String key, Object idx, double x) {
        generate(key, idx, String.valueOf(x));
    }
 
    public void generate(String key, Object idx, float x) {
        generate(key, idx, String.valueOf(x));
    }

    public void generate(String key, Object idx, int x) {
        generate(key, idx, String.valueOf(x));
    }

    public void generate(String key, Object idx, long x) {
        generate(key, idx, String.valueOf(x));
    }

    public void generate(String key, Object idx, short x) {
        generate(key, idx, String.valueOf(x));
    }

    public void generate(String key, Object idx, String value) {
        generate(key + "[" + idx + "]", value);
    }    
}
