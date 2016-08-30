package org.qualog.types;

import org.qualog.Level;
import org.qualog.output.ItemColors;

public class ElementParams {
    private final Level level;
    private final ItemColors colors;
    private final String name;
    private final int numFrames;
    
    public ElementParams(Level level, ItemColors colors, String name, int numFrames) {
        this.level = level;
        this.colors = colors;
        this.name = name;
        this.numFrames = numFrames;
    }

    public Level getLevel() {
        return level;
    }

    public ItemColors getColors() {
        return colors;
    }

    public String getName() {
        return name;
    }

    public int getNumFrames() {
        return numFrames;
    }
}
