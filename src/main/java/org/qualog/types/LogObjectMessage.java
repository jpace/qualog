package org.qualog.types;

import org.qualog.config.MessageFormat;

public class LogObjectMessage implements LogMessage {
    private final Object object;
    
    public LogObjectMessage(Object object) {
        this.object = object;
    }

    public String getMessage(MessageFormat mf) {
        return toString();
    }
    
    public String toString() {
        if (object == null) {
            return "null";
        }
        else if (LogPrimitives.isUndecorated(object)) {
            return object.toString();
        }
        else {
            StringBuilder sb = new StringBuilder();
            sb.append(object.toString());
            sb.append(" (");
            sb.append(object.getClass().getName());
            sb.append(')');
            sb.append(" #");
            sb.append(Integer.toHexString(object.hashCode()));
        
            return sb.toString();
        }
    }
}
