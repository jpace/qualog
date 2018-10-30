package org.qualog.format;

public class LineFormatter {
    private final LocationFormatter locationFormatter;
    private final ContextIdFormatter contextIdFormatter;

    public LineFormatter(ContextIdFormatter contextIdFormatter, LocationFormatter locationFormatter) {
        this.locationFormatter = locationFormatter;
        this.contextIdFormatter = contextIdFormatter;
    }    
    
    public String format(String contextId, Location location, String line) {
        StringBuilder sb = new StringBuilder();
        
        if (this.contextIdFormatter != null) {
            sb.append(this.contextIdFormatter.format(contextId)).append(" | ");
        }

        String locStr = this.locationFormatter.format(location);
        sb.append(locStr);

        if (line != null) {
            sb.append(" | ").append(line);
        }

        return sb.toString();
    }    
}
