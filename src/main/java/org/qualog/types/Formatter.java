package org.qualog.types;

import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.StringArray;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Generates lists of lines for various object types.
 */
public class Formatter extends ContainerFormatter {
    private ObjectTypes objectTypes = new ObjectTypes();

    private final Array<Object> objects;
    private final PrimitiveArrayFormatter primitiveArrays;

    public Formatter(StringArray lines, Integer limit) {
        super(lines, limit);
        
        this.objects = Array.empty();
        this.primitiveArrays = new PrimitiveArrayFormatter(lines, limit);
    }

    public Formatter(StringArray lines) {
        this(lines, null);
    }
    
    public Formatter() {
        this(StringArray.empty());
    }
    
    public Formatter(Integer limit) {
        this(StringArray.empty(), limit);
    }
    
    public void format(String key, Object value) {
        if (value == null) {
            formatNull(key);
        }
        else if (value instanceof String) {
            format(key, (String)value);
        }
        else {
            for (Object it : this.objects) {
                // by identity, not equality:
                if (it == value) {
                    formatRecursed(key, value);
                    return;
                }
            }
            this.objects.append(value);
            if (value.getClass().isArray()) {
                if (value instanceof Object[]) {
                    Object[] ary = (Object[])value;
                    format(key, ary);
                }
                else {
                    formatPrimitiveArray(key, value);
                }
            }
            else if (value instanceof Map) {
                Map<?, ?> map = (Map<?, ?>)value;
                format(key, map);
            }
            else if (value instanceof Iterable) {
                Iterable<?> itb = (Iterable<?>)value;
                format(key, itb);
            }
            else {
                String str = objectTypes.toString(value);
                format(key, str);
            }
            this.objects.takeLast();
        }
    }

    public void formatPrimitiveArray(String key, Object value) {
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
            primitiveArrays.format(key, ary);
        }
        else if (value instanceof int[]) {
            int[] ary = (int[])value;
            primitiveArrays.format(key, ary);
        }
        else if (value instanceof char[]) {
            char[] ary = (char[])value;
            primitiveArrays.format(key, ary);
        }
        else if (value instanceof float[]) {
            float[] ary = (float[])value;
            primitiveArrays.format(key, ary);
        }
        else if (value instanceof long[]) {
            long[] ary = (long[])value;
            primitiveArrays.format(key, ary);
        }
        else if (value instanceof boolean[]) {
            boolean[] ary = (boolean[])value;
            primitiveArrays.format(key, ary);
        }
        else if (value instanceof short[]) {
            short[] ary = (short[])value;
            primitiveArrays.format(key, ary);
        }
        else if (value instanceof double[]) {
            double[] ary = (double[])value;
            primitiveArrays.format(key, ary);
        }
    }

    public void format(String key, Object[] ary) {
        if (checkNull(key, ary) && checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                format(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, Throwable thr) {
        format(thr.toString());

        StackTraceElement[] stack = thr.getStackTrace();
        format(key, stack);
    }

    public <K, V> void format(String key, Map<K, V> map) {
        if (checkNull(key, map) && checkEmpty(key, map.isEmpty())) {
            int idx = 0;
            for (Map.Entry<K, V> it : map.entrySet()) {
                if (!withinLimit(idx)) {
                    break;
                }
                format(key, it.getKey(), it.getValue());
                ++idx;
            }
        }
    }

    public <T> void format(String key, Iterable<T> iterable) {
        if (checkNull(key, iterable)) {
            Iterator<T> iterator = iterable.iterator();
            if (checkEmpty(key, !iterator.hasNext())) {
                int idx = 0;
                while (iterator.hasNext() && withinLimit(idx)) {
                    T obj = iterator.next();
                    format(key, idx, obj);
                    ++idx;
                }
            }
        }        
    }

    public void format(String key, Object idx, Object value) {
        format(key + "[" + idx + "]", value);
    }

    public void formatRecursed(String key, Object value) {
        format(key, objectTypes.toString(value, "(((recursed)))"));
    }
}
