package org.qualog.output;

import org.qualog.config.ColorConfig;

import static ijdk.lang.ICore.or;
import static ijdk.lang.ICore.isEmpty;
import static ijdk.lang.ICore.isTrue;

public class Colors {
    private final ColorConfig colorConfig;
    
    public Colors(ColorConfig colorConfig) {
        this.colorConfig = colorConfig;
    }

    public ANSIColor getFileColor(ItemColors elmtColors, StackTraceElement stackElement) {
        ColorConfig cc = getColorConfig();
        return or(elmtColors.getFileColor(), cc.getFileColor(stackElement.getFileName()));
    }

    public ANSIColor getClassColor(ItemColors elmtColors, StackTraceElement stackElement) {
        ColorConfig cc = getColorConfig();
        return or(elmtColors.getClassColor(), cc.getClassColor(stackElement.getClassName()));
    }

    public ANSIColor getMethodColor(ItemColors elmtColors, StackTraceElement stackElement) {
        ColorConfig cc = getColorConfig();
        return or(elmtColors.getMethodColor(), cc.getMethodColor(stackElement.getClassName(), stackElement.getMethodName()));
    }

    public ColorConfig getColorConfig() {
        return colorConfig;
    }
    
    public ANSIColorList getMessageColors(ItemColors elmtColors, StackTraceElement ste) {
        if (!colorConfig.useColor()) {
            return null;
        }

        // the colors of the message part, not the whole line:
        ANSIColorList msgColors = elmtColors.getMessageColors();

        if (isEmpty(msgColors)) {
            ANSIColor col = or(colorConfig.getMethodColor(ste.getClassName(), ste.getMethodName()),
                               colorConfig.getClassColor(ste.getClassName()),
                               colorConfig.getFileColor(ste.getFileName()));

            if (isTrue(col)) {
                msgColors = new ANSIColorList(col);
            }
        }

        return msgColors;
    }
    
    public ItemColors getLineColors(ItemColors elmtColors, StackTraceElement stackElement) {
        // the colors of the message part, not the whole line:
        ANSIColorList msgColors = getMessageColors(elmtColors, stackElement);
        
        return new ItemColors(msgColors,
                              getFileColor(elmtColors, stackElement),
                              getClassColor(elmtColors, stackElement),
                              getMethodColor(elmtColors, stackElement));
    }
}
