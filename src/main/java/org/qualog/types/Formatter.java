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
        if (!checkNull(key, value)) {
            // nothing
        }
        else if (value instanceof String) {
            format(key, (String)value);
        }
        else if (checkRecursion(key, value)) {
            this.objects.append(value);
            if (value.getClass().isArray()) {
                if (value instanceof Object[]) {
                    Object[] ary = (Object[])value;
                    format(key, ary);
                }
                else {
                    primitiveArrays.formatArray(key, value);
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

    public boolean checkRecursion(String key, Object value) {
        for (Object it : this.objects) {
            // by identity, not equality:
            if (it == value) {
                formatRecursed(key, value);
                return false;
            }
        }
        
        return true;
    }
}
