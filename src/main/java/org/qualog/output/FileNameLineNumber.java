package org.qualog.output;

import org.incava.ijdk.lang.StringExt;
import static org.incava.ijdk.util.IUtil.*;

public class FileNameLineNumber extends Item {    
    public FileNameLineNumber(ANSIColor color, StackElements stackElements, int fileWidth) {
        super(color, stackElements, fileWidth);
    }

    public Object getValue(StackTraceElement stackElement) {
        String fileName = stackElement.getFileName();
        fileName = fileName.replace(".java", "");

        if (isRepeated()) {
            fileName = StringExt.repeat(' ', fileName.length());
        }
        
        fileName = or(fileName, "");

        int lineNum = stackElement.getLineNumber();
        String lnStr = lineNum >= 0 ? String.valueOf(lineNum) : "";

        return getSnipped(fileName) + ":" + lnStr;
    }

    public String getStackField(StackTraceElement stackElement) {
        return stackElement.getFileName();
    }

    public boolean justifyLeft() {
        //$$$ todo: switch to JustifyType
        return true;
    }

    public boolean snipIfLong() {
        return false;
    }
}