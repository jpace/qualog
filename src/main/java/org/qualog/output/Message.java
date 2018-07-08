package org.qualog.output;

import org.incava.ijdk.lang.Strings;

public class Message extends Item {
    private final String message;
    
    public Message(ANSIColorList colors, StackElements stackElements, String msg) {
        super(colors, stackElements, null);
        
        this.message = Strings.chomp(msg);
    }

    public boolean isRepeated() {
        return false;
    }

    public Object getValue(StackTraceElement stackElement) {
        return message;
    }

    public boolean snipIfLong() {
        return false;
    }
    
    public String getStackField(StackTraceElement stackElement) {
        return null;
    }
}
