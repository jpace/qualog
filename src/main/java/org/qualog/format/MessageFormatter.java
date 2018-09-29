package org.qualog.format;

import org.incava.ijdk.lang.ICore;
import org.qualog.unroller.StringFormatter;

/**
 * Generates one line, using the specified or default format.
 */
public class MessageFormatter implements StringFormatter {
    public static final String DEFAULT_KEY_VALUE_FORMAT = "%s: %s";
    public static final String DEFAULT_MESSAGE_FORMAT = "%s";
    
    private final String keyValueFormat;
    private final String messageFormat;

    public MessageFormatter() {
        this(DEFAULT_KEY_VALUE_FORMAT, DEFAULT_MESSAGE_FORMAT);
    }

    public MessageFormatter(String keyValueFormat, String messageFormat) {
        this.keyValueFormat = ICore.or(keyValueFormat, DEFAULT_KEY_VALUE_FORMAT);
        this.messageFormat = ICore.or(messageFormat, DEFAULT_MESSAGE_FORMAT);
    }
    
    public String format(String key, String value) {
        return String.format(this.keyValueFormat, key, value);
    }
    
    public String format(String msg) {
        return String.format(this.messageFormat, msg);
    }
}
