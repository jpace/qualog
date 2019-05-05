package org.qualog.unroller;

import org.incava.ijdk.collect.Array;

/**
 * Converts object types into strings for display in logging output.
 */
public class ObjectTypes {
    private static final String NONE = new String();
    
    /**
     * Primitive or quasi-primitive classes, used for toString().
     */
    private final Array<Class<?>> undecoratedTypes;

    /**
     * Creates a set of object types with their default values.
     */
    public ObjectTypes() {
        undecoratedTypes = Array.of(String.class,
                                    Number.class,
                                    Character.class,
                                    Boolean.class,
                                    StackTraceElement.class);
    }

    /**
     * Adds the type as undecorated.
     *
     * @param type the type to add
     */
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

    /**
     * Returns a string for the object.
     *
     * @param obj the object
     * @return the formatted string
     */
    public String toString(Object obj) {
        return createString(obj, NONE);
    }    

    /**
     * Returns a string for the object and message.
     *
     * @param obj the object
     * @param msg the message
     * @return the formatted string
     */
    public String toString(Object obj, String msg) {
        return createString(obj, msg);
    }

    private String createString(Object obj, String msg) {
        if (obj == null) {
            return "null";
        }
        else if (isUndecorated(obj)) {
            return obj.toString();
        }
        else {
            ObjectDecorator dec = new ObjectDecorator();
            return msg == NONE ? dec.toString(obj) : dec.toString(obj, msg);
        }
    }
}
