package org.qualog.types;

import java.io.Serializable;
import java.util.List;
import static org.incava.ijdk.util.IUtil.list;

/**
 * Elements that are not logged with their classes and hashcodes.
 */
public class LogPrimitives {
    /**
     * Primitive or quasi-primitive classes, use for toString().
     */
    private static final List<Class<? extends Serializable>> UNDECORATED_CLASSES = list(String.class,
                                                                                        Number.class,
                                                                                        Character.class,
                                                                                        Boolean.class,
                                                                                        StackTraceElement.class);
    
    /**
     * Returns whether the class of the object is assignable from any of the
     * undecorated classes.
     */
    public static boolean isUndecorated(Object obj) {
        Class<?> objCls = obj.getClass();
        for (int ci = 0; ci < UNDECORATED_CLASSES.size(); ++ci) {
            Class<?> undecCls = UNDECORATED_CLASSES.get(ci);
            if (undecCls.isAssignableFrom(objCls)) {
                return true;
            }
        }
        return false;
    }
}
