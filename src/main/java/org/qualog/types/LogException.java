package org.qualog.types;

import java.util.Map;
import java.util.Set;
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
}
