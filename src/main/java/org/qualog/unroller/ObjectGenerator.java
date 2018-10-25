package org.qualog.unroller;

import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.StringArray;
import java.util.Iterator;
import java.util.Map;

/**
 * Generates lists of lines for various object types.
 */
public class ObjectGenerator extends ContainerGenerator {
    private final ObjectTypes objectTypes;
    private final Array<Object> objects;
    private final PrimitiveArrayGenerator primitiveArrays;

    public ObjectGenerator(StringGenerator strings, Integer limit) {
        super(strings, limit);

        this.objectTypes = new ObjectTypes();
        this.objects = Array.empty();
        this.primitiveArrays = new PrimitiveArrayGenerator(strings, limit);
    }

    public ObjectGenerator(StringGenerator strings) {
        this(strings, null);
    }
    
    public void generate(String key, Object value) {
        if (checkNull(key, value)) {
            // nothing
        }
        else if (value instanceof String) {
            generate(key, (String)value);
        }
        else if (checkRecursion(key, value)) {
            // nothing
        }
        else if (value instanceof Throwable) {
            generate(key, (Throwable)value);
        }
        else {
            this.objects.append(value);
            
            if (value.getClass().isArray()) {
                generateArray(key, value);
            }
            else if (value instanceof Map) {
                Map<?, ?> map = (Map<?, ?>)value;
                generate(key, map);
            }
            else if (value instanceof Iterable) {
                Iterable<?> itb = (Iterable<?>)value;
                generate(key, itb);
            }
            else {
                String str = objectTypes.toString(value);
                generate(key, str);
            }
            
            this.objects.takeLast();
        }
    }

    public void generateArray(String key, Object value) {
        if (value instanceof Object[]) {
            Object[] ary = (Object[])value;
            generate(key, ary);
        }
        else {
            primitiveArrays.generateArray(key, value);
        }
    }

    public void generate(String key, Object[] ary) {
        if (!checkNull(key, ary) && !checkEmpty(key, ary.length)) {
            int max = getLimit(ary.length);
            for (int ai = 0; ai < max; ++ai) {
                generate(key, ai, ary[ai]);
            }
        }
    }

    public void generate(String key, Throwable thr) {
        generate(thr.toString());

        StackTraceElement[] stack = thr.getStackTrace();
        generate(key, stack);
    }

    public <K, V> void generate(String key, Map<K, V> map) {
        if (!checkNull(key, map) && !checkEmpty(key, map.isEmpty())) {
            int idx = 0;
            for (Map.Entry<K, V> it : map.entrySet()) {
                if (!withinLimit(idx)) {
                    break;
                }
                generate(key, it.getKey(), it.getValue());
                ++idx;
            }
        }
    }

    public <T> void generate(String key, Iterable<T> iterable) {
        if (!checkNull(key, iterable)) {
            Iterator<T> iterator = iterable.iterator();
            if (!checkEmpty(key, !iterator.hasNext())) {
                int idx = 0;
                while (iterator.hasNext() && withinLimit(idx)) {
                    T obj = iterator.next();
                    generate(key, idx, obj);
                    ++idx;
                }
            }
        }        
    }

    public void generate(String key, Object idx, Object value) {
        generate(key + "[" + idx + "]", value);
    }

    public void generateRecursed(String key, Object value) {
        generate(key, objectTypes.toString(value, "(((recursed)))"));
    }

    public boolean checkRecursion(String key, Object value) {
        for (Object it : this.objects) {
            // by identity, not equality:
            if (it == value) {
                generateRecursed(key, value);
                return true;
            }
        }
        
        return false;
    }
}
