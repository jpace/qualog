package org.qualog.unroller;

/**
 * An outputter for decorated objects.
 */
public class ObjectDecorator {
    public String toString(Object obj) {
        return toString(obj, obj.toString());
    }
    
    public String toString(Object obj, String msg) {
        String clsName = obj.getClass().getName();
        String hashStr = Integer.toHexString(obj.hashCode());
        StringBuilder sb = new StringBuilder(msg);
        sb.append(" (").append(clsName).append(')').append(" #").append(hashStr);
        
        return sb.toString();
    }    
}
