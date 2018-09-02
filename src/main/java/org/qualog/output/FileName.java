package org.qualog.output;

import org.incava.ijdk.lang.Strings;
import org.incava.ijdk.util.IUtil;

public class FileName extends Item {    
    public FileName(ANSIColor color, StackElements stackElements, Integer fileWidth) {
        super(color, stackElements, fileWidth);
    }
    
    public Object getValue(StackTraceElement stackElement) {
        String fileName = stackElement.getFileName();
        if (fileName == null) {
            return "";
        }
        
        fileName = fileName.replace(".java", "");
        String stackFileName = getSnipped(fileName);

        if (isRepeated()) {
            return Strings.repeat(' ', stackFileName.length());
        }
        
        return IUtil.or(stackFileName, "");
    }

    public String getStackField(StackTraceElement stackElement) {
        return stackElement.getFileName();
    }
}
