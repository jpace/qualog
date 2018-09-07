package org.qualog.types;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.incava.ijdk.collect.StringArray;
import org.qualog.Level;
import org.qualog.output.ItemColors;
import org.qualog.output.Writer;

/**
 * Wraps Java exceptions for output.
 */
public class LogException extends LogElement {
    private final Throwable thr;

    public LogException(ElementParams params, Throwable thr) {
        super(params, thr);
        this.thr = thr;
    }

    public boolean stack(Writer lw) {
        Level level = getLevel();
        ItemColors colors = getColors();
        String name = getName();
        int numFrames = getNumFrames();

        lw.stack(level, colors, name, thr.toString(), 1);

        if (numFrames > 1) {
            StackTraceElement[] stack = thr.getStackTrace();
            LogObjectArray loa = new LogObjectArray(level, colors, name, stack, numFrames);
            return loa.stack(lw);
        }
        else {
            return true;
        }
    }

    public StringArray lines(int numFrames) {
        StringArray lines = StringArray.empty();
        lines.add(thr.toString());

        if (numFrames > 0) {
            Level level = null;
            ItemColors colors = null;
            String name = getName();
            
            StackTraceElement[] stack = thr.getStackTrace();
            LogObjectArray loa = new LogObjectArray(level, colors, name, stack, numFrames);

            lines.addAll(loa.lines().get(0, numFrames - 1));
        }
        
        return lines;
    }
}
