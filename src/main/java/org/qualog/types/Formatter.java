package org.qualog.types;

import org.incava.ijdk.collect.StringArray;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Generates lists of lines for various object types.
 */
public class Formatter {
    private ObjectTypes objectTypes = new ObjectTypes();
    
    public StringArray lines(String name, Object obj) {
        String msg = objectTypes.toString(obj);
        return StringArray.of(name + ": " + msg);
    }

    public StringArray lines(String name, Object[] ary) {        
        if (ary == null || ary.length == 0) {
            return emptyLine(name);
        }
        else {
            StringArray lines = StringArray.empty();
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(lines, name, ai, ary[ai]);
            }
            return lines;
        }
    }

    public StringArray lines(String name, boolean[] ary) {        
        if (ary == null || ary.length == 0) {
            return emptyLine(name);
        }
        else {
            StringArray lines = StringArray.empty();
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(lines, name, ai, ary[ai]);
            }
            return lines;
        }
    }

    public StringArray lines(String name, byte[] ary) {        
        if (ary == null || ary.length == 0) {
            return emptyLine(name);
        }
        else {
            StringArray lines = StringArray.empty();
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(lines, name, ai, ary[ai]);
            }
            return lines;
        }
    }

    public StringArray lines(String name, char[] ary) {        
        if (ary == null || ary.length == 0) {
            return emptyLine(name);
        }
        else {
            StringArray lines = StringArray.empty();
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(lines, name, ai, ary[ai]);
            }
            return lines;
        }
    }

    public StringArray lines(String name, double[] ary) {        
        if (ary == null || ary.length == 0) {
            return emptyLine(name);
        }
        else {
            StringArray lines = StringArray.empty();
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(lines, name, ai, ary[ai]);
            }
            return lines;
        }
    }

    public StringArray lines(String name, float[] ary) {        
        if (ary == null || ary.length == 0) {
            return emptyLine(name);
        }
        else {
            StringArray lines = StringArray.empty();
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(lines, name, ai, ary[ai]);
            }
            return lines;
        }
    }

    public StringArray lines(String name, int[] ary) {        
        if (ary == null || ary.length == 0) {
            return emptyLine(name);
        }
        else {
            StringArray lines = StringArray.empty();
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(lines, name, ai, ary[ai]);
            }
            return lines;
        }
    }

    public StringArray lines(String name, long[] ary) {        
        if (ary == null || ary.length == 0) {
            return emptyLine(name);
        }
        else {
            StringArray lines = StringArray.empty();
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(lines, name, ai, ary[ai]);
            }
            return lines;
        }
    }

    public StringArray lines(String name, short[] ary) {        
        if (ary == null || ary.length == 0) {
            return emptyLine(name);
        }
        else {
            StringArray lines = StringArray.empty();
            for (int ai = 0; ai < ary.length; ++ai) {
                addLine(lines, name, ai, ary[ai]);
            }
            return lines;
        }
    }

    public StringArray lines(String name, Throwable thr, int numFrames) {
        StringArray lines = StringArray.empty();
        lines.add(thr.toString());

        if (numFrames > 0) {
            StackTraceElement[] stack = thr.getStackTrace();
            lines.addAll(lines(name, stack).get(0, numFrames - 1));
        }
        
        return lines;
    }

    public <K, V> StringArray lines(String name, Map<K, V> map) {
        if (map.isEmpty()) {
            return emptyLine(name);
        }
        else {
            StringArray lines = StringArray.empty();
            for (Map.Entry<K, V> it : map.entrySet()) {
                addLine(lines, name, it.getKey(), it.getValue());
            }
            return lines;
        }
    }

    public <T> StringArray lines(String name, Iterator<T> iterator) {
        if (iterator.hasNext()) {
            StringArray lines = StringArray.empty();
            for (int idx = 0; iterator.hasNext(); ++idx) {
                T obj = iterator.next();
                addLine(lines, name, idx, obj);
            }
            return lines;
        }
        else {
            return emptyLine(name);
        }        
    }

    public <T> StringArray lines(String name, Iterable<T> iterable) {
        return lines(name, iterable.iterator());
    }

    public <T> StringArray lines(String name, Enumeration<T> enumeration) {
        if (enumeration.hasMoreElements()) {
            StringArray lines = StringArray.empty();
            for (int idx = 0; enumeration.hasMoreElements(); ++idx) {
                T obj = enumeration.nextElement();
                addLine(lines, name, idx, obj);
            }
            return lines;
        }
        else {
            return emptyLine(name);
        }
    }

    private void addLine(StringArray lines, String name, Object key, Object value) {
        addLine(lines, name + "[" + key + "]", value);
    }

    private void addLine(StringArray lines, String name, Object msg) {
        lines.add(name + ": " + msg);
    }

    private void addLine(StringArray lines, Object msg) {
        lines.add(String.valueOf(msg));
    }

    private StringArray emptyLine(String name) {
        StringArray lines = StringArray.empty();
        addLine(lines, name, "()");
        return lines;
    }
}
