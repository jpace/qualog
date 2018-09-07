package org.qualog.types;

import org.incava.ijdk.collect.Array;

/**
 * Elements that when written to a log do not include their classes and hashcodes.
 */
public class ObjectTypes {
    /**
     * Primitive or quasi-primitive classes, used for toString().
     */
    private final Array<Class<?>> undecoratedTypes;

    public ObjectTypes() {
        undecoratedTypes = Array.of(String.class,
                                    Number.class,
                                    Character.class,
                                    Boolean.class,
                                    StackTraceElement.class);
    }
    
    /**
     * Returns whether the class of the object is assignable from any of the
     * undecorated classes.
     */
    public boolean isUndecorated(Object obj) {
        Class<?> objCls = obj.getClass();
        for (Class<?> cls : undecoratedTypes) {
            if (cls.isAssignableFrom(objCls)) {
                return true;
            }
        }
        return false;
    }

    public String toString(Object obj) {
        if (obj == null) {
            return "null";
        }
        else if (isUndecorated(obj)) {
            return obj.toString();
        }
        else {
            String clsName = obj.getClass().getName();
            String hashStr = Integer.toHexString(obj.hashCode());
            StringBuilder sb = new StringBuilder(obj.toString());
            sb.append(" (").append(clsName).append(')').append(" #").append(hashStr);
        
            return sb.toString();
        }
    }    
}
