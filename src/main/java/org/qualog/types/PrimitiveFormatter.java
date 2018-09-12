package org.qualog.types;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates lists of lines for primitives and arrays of primitives (which do not expand
 * recursively).
 */
public class PrimitiveFormatter extends BaseFormatter {
    public PrimitiveFormatter(StringArray lines, Integer limit) {
        super(lines, limit);
    }

    public PrimitiveFormatter(StringArray lines) {
        this(lines, null);
    }    
    
    public PrimitiveFormatter(Integer limit) {
        this(StringArray.empty(), limit);
    }    
    
    public void format(String key, boolean[] ary) {        
        if (ary == null) {
            formatNull(key);
        }
        else if (ary.length == 0) {
            formatEmpty(key);
        }
        else {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, byte[] ary) {        
        if (ary == null) {
            formatNull(key);
        }
        else if (ary.length == 0) {
            formatEmpty(key);
        }
        else {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, char[] ary) {        
        if (ary == null) {
            formatNull(key);
        }
        else if (ary.length == 0) {
            formatEmpty(key);
        }
        else {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, double[] ary) {
        if (ary == null) {
            formatNull(key);
        }
        else if (ary.length == 0) {
            formatEmpty(key);
        }
        else {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, float[] ary) {
        if (ary == null) {
            formatNull(key);
        }
        else if (ary.length == 0) {
            formatEmpty(key);
        }
        else {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, int[] ary) {
        if (ary == null) {
            formatNull(key);
        }
        else if (ary.length == 0) {
            formatEmpty(key);
        }
        else {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, long[] ary) {
        if (ary == null) {
            formatNull(key);
        }
        else if (ary.length == 0) {
            formatEmpty(key);
        }
        else {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, short[] ary) {
        if (ary == null) {
            formatNull(key);
        }
        else if (ary.length == 0) {
            formatEmpty(key);
        }
        else {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                format(key, ai, ary[ai]);
            }
        }
    }
    
    public void format(String key, boolean x) {
        format(key, String.valueOf(x));
    }

    public void format(String key, byte x) {
        format(key, String.valueOf(x));
    }

    public void format(String key, char x) {
        format(key, String.valueOf(x));
    }

    public void format(String key, double x) {
        format(key, String.valueOf(x));
    }
 
    public void format(String key, float x) {
        format(key, String.valueOf(x));
    }

    public void format(String key, int x) {
        format(key, String.valueOf(x));
    }

    public void format(String key, long x) {
        format(key, String.valueOf(x));
    }

    public void format(String key, short x) {
        format(key, String.valueOf(x));
    }    
    
    public void format(String key, Object idx, boolean x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, byte x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, char x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, double x) {
        format(key, idx, String.valueOf(x));
    }
 
    public void format(String key, Object idx, float x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, int x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, long x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, short x) {
        format(key, idx, String.valueOf(x));
    }

    public void format(String key, Object idx, String value) {
        format(key + "[" + idx + "]", value);
    }    
}
