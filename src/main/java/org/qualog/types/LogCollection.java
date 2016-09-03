package org.qualog.types;

import java.util.Collection;

/**
 * Wraps Collections for output.
 */
public class LogCollection extends LogObjectArray {
    public LogCollection(ElementParams params, Collection<?> coll) {
        super(params, coll.toArray());
    }
}
