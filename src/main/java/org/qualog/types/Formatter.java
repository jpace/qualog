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
    // private final Integer limit;

    public Formatter(StringArray lines) {
        this.lines = lines;
    }
    
    public Formatter() {
        this(StringArray.empty());
    }

    public StringArray getLines() {
        return lines;
    }
    
    public void format(String key, Object value) {
        if (value == null) {
            lines.add(key + ": " + null);
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
        format(key, ary, null);
    }

    public void format(String key, Object[] ary, Integer limit) {        
        if (ary == null) {
            addLine(key, null);
        }
        else if (ary.length == 0) {
            addEmptyLine(key);
        }
        else {
            int max = limit == null ? ary.length : Math.min(ary.length, limit);
            for (int ai = 0; ai < max; ++ai) {
                addLine(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, boolean[] ary) {        
        format(key, ary, null);
    }
    
    public void format(String key, boolean[] ary, Integer limit) {
        if (ary == null) {
            addLine(key, null);
        }
        else if (ary.length == 0) {
            addEmptyLine(key);
        }
        else {
            int max = limit == null ? ary.length : Math.min(ary.length, limit);
            for (int ai = 0; ai < max; ++ai) {
                addLine(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, byte[] ary) {        
        format(key, ary, null);
    }

    public void format(String key, byte[] ary, Integer limit) {        
        if (ary == null) {
            addLine(key, null);
        }
        else if (ary.length == 0) {
            addEmptyLine(key);
        }
        else {
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, char[] ary) {        
        format(key, ary, null);
    }

    public void format(String key, char[] ary, Integer limit) {
        if (ary == null) {
            addLine(key, null);
        }
        else if (ary.length == 0) {
            addEmptyLine(key);
        }
        else {
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, double[] ary) {
        format(key, ary, null);
    }

    public void format(String key, double[] ary, Integer limit) {
        if (ary == null) {
            addLine(key, null);
        }
        else if (ary.length == 0) {
            addEmptyLine(key);
        }
        else {
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, float[] ary) {
        format(key, ary, null);
    }

    public void format(String key, float[] ary, Integer limit) {
        if (ary == null) {
            addLine(key, null);
        }
        else if (ary.length == 0) {
            addEmptyLine(key);
        }
        else {
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, int[] ary) {
        format(key, ary, null);
    }

    public void format(String key, int[] ary, Integer limit) {
        if (ary == null) {
            addLine(key, null);
        }
        else if (ary.length == 0) {
            addEmptyLine(key);
        }
        else {
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, long[] ary) {
        format(key, ary, null);
    }

    public void format(String key, long[] ary, Integer limit) {
        if (ary == null) {
            addLine(key, null);
        }
        else if (ary.length == 0) {
            addEmptyLine(key);
        }
        else {
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, short[] ary) {
        format(key, ary, null);
    }

    public void format(String key, short[] ary, Integer limit) {
        if (ary == null) {
            addLine(key, null);
        }
        else if (ary.length == 0) {
            addEmptyLine(key);
        }
        else {
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(key, ai, ary[ai]);
            }
        }
    }

    public void format(String key, Throwable thr) {
        format(key, thr, null);
    }

    public void format(String key, Throwable thr, Integer numFrames) {
        lines.add(thr.toString());

        if (numFrames == null || numFrames > 0) {
            StackTraceElement[] stack = thr.getStackTrace();
            format(key, stack, numFrames);
        }        
    }

    public <K, V> void format(String key, Map<K, V> map) {
        format(key, map, null);
    }

    public <K, V> void format(String key, Map<K, V> map, Integer limit) {
        if (map.isEmpty()) {
            addEmptyLine(key);
        }
        else {
            for (Map.Entry<K, V> it : map.entrySet()) {
                addLine(key, it.getKey(), it.getValue());
            }
        }
    }

    public <T> void format(String key, Iterable<T> iterable) {
        format(key, iterable, null);
    }
        
    public <T> void format(String key, Iterable<T> iterable, Integer limit) {
        Iterator<T> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            for (int idx = 0; iterator.hasNext(); ++idx) {
                T obj = iterator.next();
                addLine(key, idx, obj);
            }
        }
        else {
            addEmptyLine(key);
        }        
    }

    private void addLine(String key, Object idx, Object value) {
        addLine(key + "[" + idx + "]", value);
    }

    private void addLine(String key, Object value) {
        if (value == null) {
            lines.add(key + ": " + null);
        }
        else if (value instanceof String) {
            addLine(key, (String)value);
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
        else if (value instanceof Iterable) {
            Iterable<?> itb = (Iterable<?>)value;
            format(key, itb);
        }
        else {
            lines.add(key + ": " + value);
        }
    }

    private void addLine(String key, String value) {
        lines.add(key + ": " + value);
    }

    private void addLine(Object value) {
        lines.add(String.valueOf(value));
    }

    private void addEmptyLine(String key) {
        addLine(key, "()");
    }
}
