package org.qualog.config;

public class MessageFormat {
    public static MessageFormat create() {
        return new MessageFormat();
    }

    public String format(String name, String objStr) {
        String format = "%s: %s";
        return String.format(format, name, objStr);
    }
}
