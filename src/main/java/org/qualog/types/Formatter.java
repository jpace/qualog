package org.qualog.types;

import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.StringArray;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Generates lists of lines for various object types.
 */
public class Formatter extends BaseFormatter {
    private ObjectTypes objectTypes = new ObjectTypes();

    private final Integer limit;
    private final Array<Object> objects;
    private final PrimitiveFormatter primitiveFormatter;

    public Formatter(StringArray lines, Integer limit) {
        super(lines, limit);
        
        this.limit = limit;
        this.objects = Array.empty();
        this.primitiveFormatter = new PrimitiveFormatter(lines, limit);
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
            format(key, "null");
        }
        else if (value instanceof String) {
            format(key, (String)value);
        }
        else {
            for (Object it : this.objects) {
                // by identity, not equality:
                if (it == value) {
                    format(key, objectTypes.toString(value, "(((recursed)))"));
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
            primitiveFormatter.format(key, ary);
        }
        else if (value instanceof int[]) {
            int[] ary = (int[])value;
            primitiveFormatter.format(key, ary);
        }
        else if (value instanceof char[]) {
            char[] ary = (char[])value;
            primitiveFormatter.format(key, ary);
        }
        else if (value instanceof float[]) {
            float[] ary = (float[])value;
            primitiveFormatter.format(key, ary);
        }
        else if (value instanceof long[]) {
            long[] ary = (long[])value;
            primitiveFormatter.format(key, ary);
        }
        else if (value instanceof boolean[]) {
            boolean[] ary = (boolean[])value;
            primitiveFormatter.format(key, ary);
        }
        else if (value instanceof short[]) {
            short[] ary = (short[])value;
            primitiveFormatter.format(key, ary);
        }
        else if (value instanceof double[]) {
            double[] ary = (double[])value;
            primitiveFormatter.format(key, ary);
        }
    }

    public void format(String key, Object[] ary) {
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

    public void format(String key, Throwable thr) {
        format(thr.toString());

        StackTraceElement[] stack = thr.getStackTrace();
        format(key, stack);
    }

    public <K, V> void format(String key, Map<K, V> map) {
        if (map.isEmpty()) {
            formatEmpty(key);
        }
        else {
            int idx = 0;
            for (Map.Entry<K, V> it : map.entrySet()) {
                if (this.limit != null && idx >= this.limit) {
                    break;
                }
                format(key, it.getKey(), it.getValue());
                ++idx;
            }
        }
    }

    public <T> void format(String key, Iterable<T> iterable) {
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) {            
            for (int idx = 0; iterator.hasNext(); ++idx) {
                if (this.limit != null && idx >= this.limit) {
                    break;
                }
                T obj = iterator.next();
                format(key, idx, obj);
            }
        }
        else {
            formatEmpty(key);
        }        
    }

    public void format(String key, Object idx, Object value) {
        format(key + "[" + idx + "]", value);
    }    
}
