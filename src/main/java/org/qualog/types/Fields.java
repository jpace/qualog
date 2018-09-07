package org.qualog.types;

import java.lang.reflect.Field;

/**
 * Accesses private fields.
 */
public class Fields {
    public static Object getValue(Object obj, Field fld) {
        Object value = null;
        boolean wasAccessible = fld.isAccessible();

        if (!wasAccessible) {
            fld.setAccessible(true);
        }

        try {
            value = fld.get(obj);
        }
        catch (IllegalAccessException iae) { // NOPMD
        }
        
        if (!wasAccessible) {
            fld.setAccessible(false);
        }
        
        return value;
    }
}
