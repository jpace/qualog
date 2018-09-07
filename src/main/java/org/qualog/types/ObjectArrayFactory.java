package org.qualog.types;

import org.incava.ijdk.lang.BooleanArray;
import org.incava.ijdk.lang.ByteArray;
import org.incava.ijdk.lang.CharArray;
import org.incava.ijdk.lang.DoubleArray;
import org.incava.ijdk.lang.FloatArray;
import org.incava.ijdk.lang.IntArray;
import org.incava.ijdk.lang.LongArray;
import org.incava.ijdk.lang.ShortArray;

/**
 * Converts primitive arrays to object arrays.
 */
public class ObjectArrayFactory {
    public Object[] toObjectArray(Object obj) {
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

    public String[] toArray(Object[] ary) {
        String[] strs = new String[ary.length];
        for (int ai = 0; ai < ary.length; ++ai) {
            strs[ai] = String.valueOf(ary[ai]);
        }
        return strs;
    }
}
