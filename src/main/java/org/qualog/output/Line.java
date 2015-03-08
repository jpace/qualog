package org.qualog.output;

import org.qualog.Configuration;
import org.qualog.config.WidthConfig;

/**
 * A log message.
 */
public class Line {
    private final String message;
    private final ItemColors colors;
    private final StackElements stackElements;
    private final Configuration config;
    
    public Line(String message,
                ItemColors colors,
                StackTraceElement stackElement, 
                StackTraceElement previousStackElement, 
                Configuration config) {
        this.message = message;
        this.colors = colors;
        this.stackElements = new StackElements(stackElement, previousStackElement);
        this.config = config;
    }

    public String getLine(boolean isRepeatedFrame, boolean verboseOutput) {
        StringBuilder sb = new StringBuilder();
        
        if (verboseOutput) {
            if (config.showFiles()) {
                appendFileName(sb);
            }
            if (config.showClasses()) {
                appendClassAndMethod(sb);
            }
        }
        String outputMsg = isRepeatedFrame ? "\"\"" : getMessage();
        sb.append(outputMsg);

        return sb.toString();
    }

    public void appendFileName(StringBuilder sb) {
        sb.append("[");

        ANSIColor color = colors.getFileColor();
        WidthConfig widths = config.getWidthConfig();
        
        if (config.useColumns()) {
            FileName lfn = new FileName(color, stackElements, widths.getFileWidth());
            String flstr = lfn.getFormatted();

            LineNumber lln = new LineNumber(color, stackElements, widths.getLineWidth());
            String lnstr = lln.getFormatted();
            sb.append(flstr).append(' ').append(lnstr);
        }
        else {
            FileNameLineNumber lfnln = new FileNameLineNumber(color, stackElements, widths.getFileWidth());
            sb.append(lfnln.getFormatted());
        }

        sb.append("] ");
    }

    public void appendClassAndMethod(StringBuilder sb) {
        WidthConfig widths = config.getWidthConfig();

        sb.append("{");

        ClassName lcn = new ClassName(colors.getClassColor(), stackElements, widths.getClassWidth());
        sb.append(lcn.getFormatted());
        
        sb.append('#');

        MethodName lmn = new MethodName(colors.getMethodColor(), stackElements, widths.getFunctionWidth());
        sb.append(lmn.getFormatted());

        sb.append("} ");
    }

    public String getMessage() {
        Message lm = new Message(colors.getMessageColors(), stackElements, message);
        return lm.getFormatted();
    }
}
