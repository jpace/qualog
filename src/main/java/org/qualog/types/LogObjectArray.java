package org.qualog.types;

import org.incava.ijdk.collect.StringArray;
import org.qualog.Level;
import org.qualog.output.ItemColors;
import org.qualog.output.Writer;
import org.qualog.unroller.Formatter;
import org.qualog.unroller.StringArrayWriter;
import org.qualog.unroller.StringFormatter;
import org.qualog.unroller.StringGenerator;

/**
 * Wraps C-style arrays for output.
 */
public class LogObjectArray extends LogElement {
    private final Object[] ary;
    
    public LogObjectArray(Level level, ItemColors colors, String name, Object[] ary, int numFrames) {
        this(new ElementParams(level, colors, name, numFrames), ary);
    }
    
    public LogObjectArray(ElementParams params, Object[] ary) {
        super(params, ary);
        this.ary = ary;
    }

    public boolean stack(Writer lw) {
        Level level = getLevel();
        ItemColors colors = getColors();
        String name = getName();
        int numFrames = getNumFrames();

        // @todo: add usage of writer
        if (ary == null || ary.length == 0) {
            return stackEmptyCollection(lw);
        }

        boolean ret = true;
        for (int ai = 0; ai < ary.length; ++ai) {
            int nFrames = ai == ary.length - 1 ? numFrames : 1;
            ret = lw.stack(level, colors, name + "[" + ai + "]", ary[ai], nFrames);
        }
        return ret;
    }

    public StringArray lines() {
        StringArray lines = StringArray.empty();
        StringGenerator sg = new StringGenerator(new StringFormatter(StringFormatter.DEFAULT_FORMAT), new StringArrayWriter(lines));
        new Formatter(sg).format(getName(), ary);
        return lines;
    }   
}
