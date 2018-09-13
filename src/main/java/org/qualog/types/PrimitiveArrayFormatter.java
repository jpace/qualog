package org.qualog.types;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for arrays of primitives (which, not being Objects, do not expand
 * recursively).
 */
public class PrimitiveArrayFormatter extends ContainerFormatter {
    private PrimitiveFormatter primitives;
    
    public PrimitiveArrayFormatter(StringArray lines, Integer limit) {
        super(lines, limit);
        
        this.primitives = new PrimitiveFormatter(lines);
    }

    public PrimitiveArrayFormatter(StringArray lines) {
        this(lines, null);
    }    
    
    public PrimitiveArrayFormatter(Integer limit) {
        this(StringArray.empty(), limit);
    }    
    
    public PrimitiveArrayFormatter() {
        this(StringArray.empty(), null);
    }    
    
    public void format(String key, boolean[] ary) {
        if (checkNull(key, ary) && checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, byte[] ary) {        
        if (checkNull(key, ary) && checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, char[] ary) {        
        if (checkNull(key, ary) && checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, double[] ary) {
        if (checkNull(key, ary) && checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, float[] ary) {
        if (checkNull(key, ary) && checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, int[] ary) {
        if (checkNull(key, ary) && checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, long[] ary) {
        if (checkNull(key, ary) && checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, short[] ary) {
        if (checkNull(key, ary) && checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                primitives.format(key, ai, ary[ai]);
            }
        }
    }
}
