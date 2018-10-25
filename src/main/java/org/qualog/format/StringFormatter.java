package org.qualog.format;

import org.incava.ijdk.collect.StringArray;

/**
 * Generates a string for the key/value or message.
 */
public interface StringFormatter {
    public String format(String key, String value);
    
    public String format(String msg);
}
