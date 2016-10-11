package org.qualog.config;

public class MessageFormat {
    private static String format = "%s: %s";
    
    public static MessageFormat create() {
        return new MessageFormat();
    }

    public static void setFormat(String fmt) {
        format = fmt;
    }

    public String format(String name, String objStr) {
        return String.format(format, name, objStr);
    }
}
