package org.qualog.types;

import org.incava.ijdk.lang.*;
import org.qualog.Level;
import org.qualog.output.Writer;
import org.qualog.output.ItemColors;

/**
 * Wraps C-style arrays for output.
 */
public class LogObjectArray extends LogElement {
    public static LogObjectArray create(Level level, ItemColors colors, String name, Object obj, int numFrames) {
        Object[] objAry = toObjectArray(obj);
        return new LogObjectArray(level, colors, name, objAry, numFrames);
    }

    public static LogObjectArray create(ElementParams params, Object obj) {
        Object[] objAry = toObjectArray(obj);
        return new LogObjectArray(params, objAry);
    }

    public static Object[] toObjectArray(Object obj) {
        if (obj == null) {
            return null;
        }
        else if (obj instanceof Object[]) {
            return (Object[])obj;
        }
        else if (obj instanceof boolean[]) {
            boolean[] ary = (boolean[])obj;
            return BooleanArray.toStringArray(ary);
        }
        else if (obj instanceof byte[]) {
            byte[] ary = (byte[])obj;
            return ByteArray.toStringArray(ary);
        }
        else if (obj instanceof char[]) {
            char[] ary = (char[])obj;
            return CharArray.toStringArray(ary);
        }
        else if (obj instanceof double[]) {
            double[] ary = (double[])obj;
            return DoubleArray.toStringArray(ary);
        } 
        else if (obj instanceof float[]) {
            float[] ary = (float[])obj;
            return FloatArray.toStringArray(ary);
        }
        else if (obj instanceof int[]) {
            int[] ary = (int[])obj;
            return IntArray.toStringArray(ary);
        }
        else if (obj instanceof long[]) {
            long[] ary = (long[])obj;
            return LongArray.toStringArray(ary);
        }
        else if (obj instanceof short[]) {
            short[] ary = (short[])obj;
            return ShortArray.toStringArray(ary);
        }
        else {
            return null;
        }
    }

    private final Object[] ary;
    
    public LogObjectArray(Level level, ItemColors colors, String name, Object[] ary, int numFrames) {
        this(new ElementParams(level, colors, name, numFrames), ary);
    }
    
    public LogObjectArray(ElementParams params, Object[] ary) {
        super(params, ary);
        this.ary = ary;
    }

    public boolean stack(Writer lw) {
        Level level = getLevel();
        ItemColors colors = getColors();
        String name = getName();
        int numFrames = getNumFrames();

        // @todo: add usage of writer
        if (ary == null || ary.length == 0) {
            return stackEmptyCollection(lw);
        }

        boolean ret = true;
        for (int ai = 0; ai < ary.length; ++ai) {
            int nFrames = ai == ary.length - 1 ? numFrames : 1;
            ret = lw.stack(level, colors, name + "[" + ai + "]", ary[ai], nFrames);
        }
        return ret;
    }
}
