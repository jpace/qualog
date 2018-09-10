package org.qualog.types;

import org.incava.ijdk.collect.StringArray;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Generates lists of lines for various object types.
 */
public class Formatter {
    private ObjectTypes objectTypes = new ObjectTypes();

    private final StringArray lines;
    private final Integer limit;

    public Formatter(StringArray lines, Integer limit) {
        this.lines = lines;
        this.limit = limit;
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

    public StringArray getLines() {
        return lines;
    }
    
    public void format(String key, Object value) {
        if (value == null) {
            lines.add(key + ": " + null);
        }
        else if (value instanceof String) {
            format(key, (String)value);
        }
        else if (value.getClass().isArray()) {
            if (value instanceof Object[]) {
                Object[] ary = (Object[])value;
                format(key, ary);
            }
            else if (value instanceof boolean[]) {
                boolean[] ary = (boolean[])value;
                format(key, ary);
            }
            else if (value instanceof byte[]) {
                byte[] ary = (byte[])value;
                format(key, ary);
            }
            else if (value instanceof char[]) {
                char[] ary = (char[])value;
                format(key, ary);
            }
            else if (value instanceof double[]) {
                double[] ary = (double[])value;
                format(key, ary);
            }
            else if (value instanceof float[]) {
                float[] ary = (float[])value;
                format(key, ary);
            }
            else if (value instanceof int[]) {
                int[] ary = (int[])value;
                format(key, ary);
            }
            else if (value instanceof long[]) {
                long[] ary = (long[])value;
                format(key, ary);
            }
            else if (value instanceof short[]) {
                short[] ary = (short[])value;
                format(key, ary);
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
    }

    public void format(String key, String value) {
        lines.add(key + ": " + value);
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

    public void format(String key, Throwable thr) {
        lines.add(thr.toString());

        if (this.limit == null || this.limit > 0) {
            StackTraceElement[] stack = thr.getStackTrace();
            format(key, stack);
        }        
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

    private void addLine(String key, String value) {
        lines.add(key + ": " + value);
    }

    private void addLine(Object value) {
        lines.add(String.valueOf(value));
    }

    private void formatEmpty(String key) {
        format(key, "()");
    }

    private void formatNull(String key) {
        format(key, "null");
    }

    private int getLimit(int size) {
        return this.limit == null ? size : Math.min(size, this.limit);
    }
}
