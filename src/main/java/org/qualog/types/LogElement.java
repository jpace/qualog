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
    private final ElementParams params;
    private final Object object;
        
    public LogElement(Level level, ItemColors colors, String name, Object obj, int numFrames) {
        this(new ElementParams(level, colors, name, numFrames), obj);
    }
        
    public LogElement(ElementParams params, Object obj) {
        this.params = params;
        this.object = obj;
    }

    public Level getLevel() {
        return params.getLevel();
    }
    
    public ItemColors getColors() {
        return params.getColors();
    }

    public String getName() {
        return params.getName();
    }

    public Object getObject() {
        return object;
    }

    public int getNumFrames() {
        return params.getNumFrames();
    }
    
    public String getMessage() {
        String nm = getName();
        return (nm == null ? "" : (nm + ": ")) + toString(object);
    }

    public boolean stack(Writer lw) {
        return lw.stack(this);
    }

    public boolean stackEmptyCollection(Writer lw) {
        return lw.stack(getLevel(), getColors(), getName(), "()", getNumFrames());
    }

    public String toString(Object obj) {
        if (isNull(obj)) {
            return "null";
        }
        else if (LogPrimitives.isUndecorated(obj)) {
            return obj.toString();
        }
        else {
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
}
