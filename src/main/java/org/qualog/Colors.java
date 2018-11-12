package org.qualog;

import org.qualog.output.ANSIColor;

public class Colors {
    public static String none(String str) {
        return ANSIColor.NONE.toString(str);
    }
    
    public static String reset(String str) {
        return ANSIColor.RESET.toString(str);
    }
    
    public static String bold(String str) {
        return ANSIColor.BOLD.toString(str);
    }
    
    public static String underscore(String str) {
        return ANSIColor.UNDERSCORE.toString(str);
    }
    
    public static String underline(String str) {
        return ANSIColor.UNDERLINE.toString(str);
    }
    
    public static String blink(String str) {
        return ANSIColor.BLINK.toString(str);
    }
    
    public static String reverse(String str) {
        return ANSIColor.REVERSE.toString(str);
    }
    
    public static String concealed(String str) {
        return ANSIColor.CONCEALED.toString(str);
    }
    
    public static String black(String str) {
        return ANSIColor.BLACK.toString(str);
    }
    
    public static String red(String str) {
        return ANSIColor.RED.toString(str);
    }
    
    public static String green(String str) {
        return ANSIColor.GREEN.toString(str);
    }
    
    public static String yellow(String str) {
        return ANSIColor.YELLOW.toString(str);
    }
    
    public static String blue(String str) {
        return ANSIColor.BLUE.toString(str);
    }
    
    public static String magenta(String str) {
        return ANSIColor.MAGENTA.toString(str);
    }
    
    public static String cyan(String str) {
        return ANSIColor.CYAN.toString(str);
    }
    
    public static String white(String str) {
        return ANSIColor.WHITE.toString(str);
    }
    
    public static String onBlack(String str) {
        return ANSIColor.ON_BLACK.toString(str);
    }
    
    public static String onRed(String str) {
        return ANSIColor.ON_RED.toString(str);
    }
    
    public static String onGreen(String str) {
        return ANSIColor.ON_GREEN.toString(str);
    }
    
    public static String onYellow(String str) {
        return ANSIColor.ON_YELLOW.toString(str);
    }
    
    public static String onBlue(String str) {
        return ANSIColor.ON_BLUE.toString(str);
    }
    
    public static String onMagenta(String str) {
        return ANSIColor.ON_MAGENTA.toString(str);
    }
    
    public static String onCyan(String str) {
        return ANSIColor.ON_CYAN.toString(str);
    }
    
    public static String onWhite(String str) {
        return ANSIColor.ON_WHITE.toString(str);
    }
}
