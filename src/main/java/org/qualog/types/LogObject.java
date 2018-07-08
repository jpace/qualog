package org.qualog.types;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;
import org.qualog.Level;

/**
 * Wraps logging for an object.
 */
public class LogObject {
    public enum InspectOptionType { INCLUDE_SUPERCLASSES, INCLUDE_STATIC };

    public static Map<String, Object> inspect(Object obj) {
        return inspect(EnumSet.noneOf(InspectOptionType.class), obj);
    }

    public static Map<String, Object> inspect(EnumSet<InspectOptionType> inspOpts, Object obj) {
        return obj == null ? null : inspectForClass(inspOpts, obj.getClass(), obj);
    }

    public static Map<String, Object> inspectFully(Object obj) {
        return inspectFully(EnumSet.of(InspectOptionType.INCLUDE_SUPERCLASSES), obj);
    }

    public static Map<String, Object> inspectFully(EnumSet<InspectOptionType> inspOpts, Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> attributes = new TreeMap<String, Object>();
        Class<?> cls = obj.getClass();

        while (cls != null) {
            attributes.putAll(inspectForClass(inspOpts, cls, obj));
            cls = inspOpts.contains(InspectOptionType.INCLUDE_SUPERCLASSES) ? cls.getSuperclass() : null;
        }
        return attributes;
    }

    public static Map<String, Object> inspectForClass(EnumSet<InspectOptionType> inspOpts, Class<?> cls, Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> attributes = new TreeMap<String, Object>();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field fld : fields) {
            getFieldValue(inspOpts, obj, fld, attributes);
        }

        return attributes;
    }

    public static void getFieldValue(EnumSet<InspectOptionType> inspOpts, Object obj, Field fld, Map<String, Object> attributes) {
        int mods = fld.getModifiers();

        if (Modifier.isStatic(mods) && !inspOpts.contains(InspectOptionType.INCLUDE_STATIC)) {
            return;
        }

        boolean wasAccessible = fld.isAccessible();

        if (!wasAccessible) {
            fld.setAccessible(true);
        }

        try {
            Object val = fld.get(obj);
            attributes.put(fld.getName(), val);
        }
        catch (IllegalAccessException iae) { // NOPMD
        }

        if (!wasAccessible) {
            fld.setAccessible(false);
        }
    }
}
