package org.qualog;

import org.qualog.output.ItemColors;
import org.qualog.types.LogElement;

public class LogSplitter extends LogElement {
    public static String splitAndJoined(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); ++i) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
    
    public LogSplitter(Level level, ItemColors colors, String name, Object obj, int numFrames) {
        super(level, colors, name, splitAndJoined(obj.toString()), numFrames);
    }
}
