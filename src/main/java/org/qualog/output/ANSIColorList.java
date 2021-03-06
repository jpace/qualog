package org.qualog.output;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * A list (set) of colors.
 */
public class ANSIColorList extends ArrayList<ANSIColor> {
    public static final long serialVersionUID = 1;
    
    public ANSIColorList(EnumSet<ANSIColor> colors) {
        addAll(colors);
    }

    public ANSIColorList(ANSIColor color) {
        add(color);
    }

    public ANSIColorList() {
    }

    /**
     * Wraps the given string in these colors, ending it with NONE (reset).
     * 
     * @param str the string to wrap
     * @return the wrapped string
     */
    public String toString(String str) {
        if (isEmpty()) {
            return str;
        }

        StringBuffer sb = new StringBuffer();
        for (ANSIColor color : this) {
            sb.append(color);
        }
        sb.append(str);
        sb.append(ANSIColor.NONE);
        return sb.toString();
    }
}
