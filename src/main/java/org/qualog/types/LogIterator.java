package org.qualog.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Wraps Iterators for output.
 */
public class LogIterator extends LogCollection {
    public static <T> LogIterator create(ElementParams params, Iterator<T> it) {
        return new LogIterator(params, it);
    }

    public static <T> Collection<T> iteratorToCollection(Iterator<T> it) {
        Collection<T> coll = new ArrayList<T>();
        while (it.hasNext()) {
            coll.add(it.next());
        }
        return coll;
    }

    public <T> LogIterator(ElementParams params, Iterator<T> it) {
        super(params, iteratorToCollection(it));
    }
}
