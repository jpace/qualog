package org.qualog.types;

import org.qualog.Level;
import org.qualog.config.MessageFormat;
import org.qualog.output.ItemColors;
import org.qualog.output.Writer;

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

    protected Level getLevel() {
        return params.getLevel();
    }
    
    public ItemColors getColors() {
        return params.getColors();
    }

    protected String getName() {
        return params.getName();
    }

    protected Object getObject() {
        return object;
    }

    public int getNumFrames() {
        return params.getNumFrames();
    }
    
    public String getMessage() {
        MessageFormat mf = MessageFormat.create();
        String name = getName();
        LogMessage lm = name == null ? new LogObjectMessage(object) : new LogNameObjectMessage(name, object);
        return lm.getMessage(mf);
    }

    public boolean stack(Writer lw) {
        return lw.stack(this);
    }

    protected boolean stackEmptyCollection(Writer lw) {
        return lw.stack(params.getLevel(), params.getColors(), params.getName(), "()", params.getNumFrames());
    }
}
