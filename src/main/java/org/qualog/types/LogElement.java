package org.qualog.types;

import java.io.Serializable;
import java.util.List;
import org.qualog.Level;
import org.qualog.output.ItemColors;
import org.qualog.output.Writer;
import static org.incava.ijdk.util.IUtil.*;

/**
 * An item that can be logged.
 *
 * @see org.qualog.Log
 */
public class LogElement {
    /**
     * Primitive or quasi-primitive classes, use for toString().
     */
    public static final List<Class<? extends Serializable>> UNDECORATED_CLASSES = list(String.class,
                                                                                       Number.class,
                                                                                       Character.class,
                                                                                       Boolean.class,
                                                                                       StackTraceElement.class);
    
    /**
     * Returns whether the class of the object is assignable from any of the
     * undecorated classes.
     */
    protected static boolean isUndecorated(Object obj) {
        Class<?> objCls = obj.getClass();
        for (int ci = 0; ci < UNDECORATED_CLASSES.size(); ++ci) {
            Class<?> undecCls = UNDECORATED_CLASSES.get(ci);
            if (undecCls.isAssignableFrom(objCls)) {
                return true;
            }
        }
        return false;
    }

    private final Level level;
    private final ItemColors colors;
    private final String name;
    private final Object object;
    private final int numFrames;
        
    public LogElement(Level level, ItemColors colors, String name, Object obj, int numFrames) {
        this.level = level;
        this.colors = colors;
        this.name = name;
        this.object = obj;
        this.numFrames = numFrames;
    }
        
    public LogElement(ElementParams params, Object obj) {
        this(params.getLevel(), params.getColors(), params.getName(), obj, params.getNumFrames());
    }

    public Level getLevel() {
        return level;
    }
    
    public ItemColors getColors() {
        return colors;
    }

    public String getName() {
        return name;
    }

    public Object getObject() {
        return object;
    }

    public int getNumFrames() {
        return numFrames;
    }
    
    public String getMessage() {
        String nm = getName();
        return (nm == null ? "" : (nm + ": ")) + toString(object);
    }

    public boolean stack(Writer lw) {
        return lw.stack(this);
    }

    public boolean stackEmptyCollection(Writer lw) {
        return lw.stack(level, colors, name, "()", numFrames);
    }

    public String toString(Object obj) {
        if (isNull(obj)) {
            return "null";
        }

        if (isUndecorated(obj)) {
            return obj.toString();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(obj.toString());
        sb.append(" (");
        sb.append(obj.getClass().getName());
        sb.append(')');
        sb.append(" #");
        sb.append(Integer.toHexString(obj.hashCode()));
        
        return sb.toString();
    }
}
