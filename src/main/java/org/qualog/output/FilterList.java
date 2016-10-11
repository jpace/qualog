package org.qualog.output;

import java.util.ArrayList;
import org.qualog.Filter;
import org.qualog.Level;

public class FilterList extends ArrayList<Filter> {
    private static final long serialVersionUID = 5942735615057212057L;
    
    public boolean isLoggable(StackTraceElement stackElement, Level level) {
        boolean isLoggable = true;
        for (Filter filter : this) {
            Level flevel = filter.getLevel();
            if (filter.isMatch(stackElement)) {
                isLoggable = flevel != null && level.compareTo(flevel) >= 0;
            }
        }
        return isLoggable;
    }
}
