package org.qualog.unroller;

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

    public void addUndecoratedType(Class<?> type) {
        undecoratedTypes.add(type);
    }
    
    /**
     * Returns whether the class of the object is assignable from any of the undecorated classes.
     * Decorated classes have more extensive output.
     *
     * @param obj the object
     * @return whether the object is not decorated
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
            return new ObjectDecorator().toString(obj);
        }
    }    

    public String toString(Object obj, String msg) {
        if (obj == null) {
            return "null";
        }
        else if (isUndecorated(obj)) {
            return obj.toString();
        }
        else {
            return new ObjectDecorator().toString(obj, msg);
        }
    }
}
