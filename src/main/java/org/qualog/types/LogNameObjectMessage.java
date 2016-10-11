package org.qualog.types;

import org.qualog.config.MessageFormat;

public class LogNameObjectMessage extends LogObjectMessage {
    private final String name;
    
    public LogNameObjectMessage(String name, Object object) {
        super(object);
        this.name = name;
    }

    public String getMessage(MessageFormat mf) {
        String objMsg = super.getMessage(mf);
        return mf.format(name, objMsg);
    }
}
