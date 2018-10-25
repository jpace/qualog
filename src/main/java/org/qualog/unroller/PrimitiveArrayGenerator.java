package org.qualog.unroller;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for arrays of primitives (which, not being Objects, do not expand
 * recursively).
 */
public class PrimitiveArrayGenerator extends ContainerGenerator {
    private PrimitiveGenerator primitives;    
    
    public PrimitiveArrayGenerator(StringGenerator strings, Integer limit) {
        super(strings, limit);
        
        this.primitives = new PrimitiveGenerator(strings);
    }
    
    public PrimitiveArrayGenerator(StringGenerator strings) {
        this(strings, null);
    }
    
    public void generateArray(String key, Object value) {
        // frequency in the android sdk, so that's the evaluation order:
        // 4456 byte
        // 2558 int
        // 820 char
        // 816 float
        // 346 long
        // 171 boolean
        // 144 short
        // 99 double

        if (value instanceof byte[]) {
            byte[] ary = (byte[])value;
            generate(key, ary);
        }
        else if (value instanceof int[]) {
            int[] ary = (int[])value;
            generate(key, ary);
        }
        else if (value instanceof char[]) {
            char[] ary = (char[])value;
            generate(key, ary);
        }
        else if (value instanceof float[]) {
            float[] ary = (float[])value;
            generate(key, ary);
        }
        else if (value instanceof long[]) {
            long[] ary = (long[])value;
            generate(key, ary);
        }
        else if (value instanceof boolean[]) {
            boolean[] ary = (boolean[])value;
            generate(key, ary);
        }
        else if (value instanceof short[]) {
            short[] ary = (short[])value;
            generate(key, ary);
        }
        else if (value instanceof double[]) {
            double[] ary = (double[])value;
            generate(key, ary);
        }
    }    
    
    public void generate(String key, boolean[] ary) {
        if (!checkNull(key, ary) && !checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.generate(key, ai, ary[ai]);
            }
        }
    }

    public void generate(String key, byte[] ary) {        
        if (!checkNull(key, ary) && !checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.generate(key, ai, ary[ai]);
            }
        }
    }

    public void generate(String key, char[] ary) {        
        if (!checkNull(key, ary) && !checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.generate(key, ai, ary[ai]);
            }
        }
    }

    public void generate(String key, double[] ary) {
        if (!checkNull(key, ary) && !checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.generate(key, ai, ary[ai]);
            }
        }
    }

    public void generate(String key, float[] ary) {
        if (!checkNull(key, ary) && !checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.generate(key, ai, ary[ai]);
            }
        }
    }

    public void generate(String key, int[] ary) {
        if (!checkNull(key, ary) && !checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.generate(key, ai, ary[ai]);
            }
        }
    }

    public void generate(String key, long[] ary) {
        if (!checkNull(key, ary) && !checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.generate(key, ai, ary[ai]);
            }
        }
    }

    public void generate(String key, short[] ary) {
        if (!checkNull(key, ary) && !checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.generate(key, ai, ary[ai]);
            }
        }
    }
}
