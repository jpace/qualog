package org.qualog.types;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;
import org.qualog.Level;

/**
 * Gets values of instance variables.
 */
public class LogInspectable {
    public enum InspectOptionType { INCLUDE_SUPERCLASSES, INCLUDE_STATIC };

    public static Map<String, Object> inspect(Object obj) {
        return inspect(EnumSet.noneOf(InspectOptionType.class), obj);
    }

    public static Map<String, Object> inspect(EnumSet<InspectOptionType> inspOpts, Object obj) {
        if (obj == null) {
            return null;
        }

        boolean includeSuperclasses = inspOpts.contains(InspectOptionType.INCLUDE_SUPERCLASSES);
        boolean includeStatic = inspOpts.contains(InspectOptionType.INCLUDE_STATIC);
        
        Map<String, Object> attributes = new TreeMap<String, Object>();
        
        Class<?> cls = obj.getClass();
        while (cls != null) {
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (includeStatic || !Modifier.isStatic(field.getModifiers())) {
                    Object value = Fields.getValue(obj, field);
                    attributes.put(field.getName(), value);
                }
            }
            cls = includeSuperclasses ? cls.getSuperclass() : null;
        }

        return attributes;
    }
}
