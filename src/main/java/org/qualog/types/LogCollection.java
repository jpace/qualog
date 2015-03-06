package org.qualog.types;

import java.util.Collection;
import org.qualog.Level;
import org.qualog.output.ItemColors;

/**
 * Wraps Collections for output.
 */
public class LogCollection extends LogObjectArray {
    private final Collection<?> coll;

    public LogCollection(Level level, ItemColors colors, String name, Collection<?> coll, int numFrames) {
        super(level, colors, name, coll.toArray(), numFrames);
        this.coll = coll;
    }
}
