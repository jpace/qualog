package org.qualog.format;

public class Fields {
    public static final String DEFAULT_REPEAT_FILE_NAME = "\"\"";
    public static final String DEFAULT_REPEAT_CLASS_NAME = "\"\"";
    public static final String DEFAULT_REPEAT_METHOD_NAME = "\"\"";
    public static final String DEFAULT_STACK_MESSAGE = "^";

    private final String repeatFileName;
    private final String repeatClassName;
    private final String repeatMethodName;
    private final String stackMessage;

    public Fields(String repeatFileName, String repeatClassName, String repeatMethodName, String stackMessage) {
        this.repeatFileName = repeatFileName;
        this.repeatClassName = repeatClassName;
        this.repeatMethodName = repeatMethodName;
        this.stackMessage = stackMessage;
    }

    public Fields() {
        this(DEFAULT_REPEAT_FILE_NAME, DEFAULT_REPEAT_CLASS_NAME, DEFAULT_REPEAT_METHOD_NAME, DEFAULT_STACK_MESSAGE);
    }

    public String getRepeatFileName() {
        return repeatFileName;
    }

    public String getRepeatClassName() {
        return repeatClassName;
    }

    public String getRepeatMethodName() {
        return repeatMethodName;
    }

    public String getStackMessage() {
        return stackMessage;
    }    
}
