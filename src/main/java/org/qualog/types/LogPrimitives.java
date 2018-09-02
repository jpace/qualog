package org.qualog.types;

import org.incava.ijdk.collect.Array;

/**
 * Elements that when written to a log do not include their classes and hashcodes.
 */
public class LogPrimitives {
    /**
     * Primitive or quasi-primitive classes, used for toString().
     */
    private final Array<Class<?>> undecoratedClasses;

    public LogPrimitives() {
        undecoratedClasses = Array.of(String.class,
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
        for (Class<?> undecCls : undecoratedClasses) {
            if (undecCls.isAssignableFrom(objCls)) {
                return true;
            }
        }
        return false;
    }
}
