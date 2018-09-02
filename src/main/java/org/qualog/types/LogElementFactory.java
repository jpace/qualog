package org.qualog.types;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.qualog.Level;
import org.qualog.output.ItemColors;

public class LogElementFactory {
    private static final Map<Class<?>, Class<? extends LogElement>> clsToElmtClasses = new HashMap<Class<?>, Class<? extends LogElement>>();
    
    public static void add(Class<?> cls, Class<? extends LogElement> elmtCls) {
        clsToElmtClasses.put(cls, elmtCls);
    }

    public static Class<? extends LogElement> findElmtClass(Object obj) {
        Class<?> objCls = obj.getClass();
        for (Map.Entry<Class<?>, Class<? extends LogElement>> entry : clsToElmtClasses.entrySet()) {
            if (entry.getKey().isAssignableFrom(objCls)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static LogElement createLogElement(Class<? extends LogElement> elmtCls, Level level, ItemColors colors, String name, Object obj, int numFrames) {
        try {
            Constructor<?> ctor = elmtCls.getConstructor(Level.class, ItemColors.class, String.class, Object.class, int.class);
            return ctor == null ? null : (LogElement)ctor.newInstance(level, colors, name, obj, numFrames);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static LogElement createLogElement(Class<? extends LogElement> elmtCls, ElementParams params, Object obj) {
        try {
            Constructor<?> ctor = elmtCls.getConstructor(ElementParams.class, Object.class);
            return ctor == null ? null : (LogElement)ctor.newInstance(params, obj);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static LogElement create(Level level, ItemColors colors, String name, Object obj, int numFrames) {
        ElementParams params = new ElementParams(level, colors, name, numFrames);
        
        if (obj == null) {
            return new LogElement(params, obj);
        }
        else {
            Class<? extends LogElement> elmtCls = findElmtClass(obj);
            if (elmtCls != null) {
                LogElement elmt = createLogElement(elmtCls, level, colors, name, obj, numFrames);
                return elmt == null ? createLogElement(elmtCls, params, obj) : elmt;
            }
        }

        if (obj.getClass().isArray()) {
            return LogObjectArray.create(params, obj);
        }
        else if (obj instanceof Collection) {
            Collection<?> coll = (Collection<?>)obj;
            return new LogCollection(params, coll);
        }
        else if (obj instanceof Iterator) {
            Iterator<?> it = (Iterator<?>)obj;
            return LogIterator.create(params, it);
        }
        else if (obj instanceof Enumeration) {
            Enumeration<?> en = (Enumeration<?>)obj;
            return new LogEnumeration(params, en);
        }
        else if (obj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>)obj;
            return new LogMap(params, map);
        }
        else if (obj instanceof Throwable) {
            Throwable thr = (Throwable)obj;
            return new LogException(params, thr);
        }
        else {
            return new LogElement(params, obj);
        }
    }
}
