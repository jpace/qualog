package org.incava.qualog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Wraps Iterators for output.
 */
public class QlIterator {
    public static <T> boolean stack(QlLevel level, 
                                    ANSIColor[] msgColors,
                                    String name,
                                    Iterator<T> it,
                                    ANSIColor fileColor,
                                    ANSIColor classColor,
                                    ANSIColor methodColor,
                                    int numFrames) {
        Collection<T> ary = new ArrayList<T>();
        while (it.hasNext()) {
            ary.add(it.next());
        }

        return QlCollection.stack(level, msgColors, name, ary, fileColor, classColor, methodColor, numFrames);
    }
}
