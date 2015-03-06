package org.qualog.types;

import java.util.Map;
import java.util.Set;
import org.qualog.Level;
import org.qualog.output.ItemColors;
import org.qualog.output.Writer;

/**
 * Wraps Java maps for output.
 */
public class LogMap extends LogElement {
    private final Map<?, ?> map;

    public LogMap(Level level, ItemColors colors, String name, Map<?,?> map, int numFrames) {
        super(level, colors, name, map, numFrames);
        this.map = map;
    }

    public boolean stack(Writer lw) {
        Level level = getLevel();
        ItemColors colors = getColors();
        String name = getName();
        int numFrames = getNumFrames();

        if (map.isEmpty()) {
            return stackEmptyCollection(lw);
        }
        
        Set<?> keySet = map.keySet();
        Object[] keys = keySet.toArray();
        boolean ret = true;
        for (int ki = 0; ki < keys.length; ++ki) {
            int nFrames = ki == keys.length - 1 ? numFrames : 1;
            ret = lw.stack(level, colors, name + "[" + keys[ki] + "]", map.get(keys[ki]), nFrames);
        }
        return ret;
    }
}
