package org.qualog.format;

public class Location {
    private final String fileName;
    private final Integer lineNumber;
    private final String className;
    private final String methodName;
    
    public Location(String fileName, Integer lineNumber, String className, String methodName) {
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.className = className;
        this.methodName = methodName;
    }
        
    public Location(StackTraceElement frame) {
        this(frame.getFileName(), frame.getLineNumber(), frame.getClassName(), frame.getMethodName());
    }

    public String getFileName() {
        return fileName;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }
}
