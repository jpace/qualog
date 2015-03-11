package org.qualog.config;

import java.util.HashMap;
import java.util.Map;
import org.qualog.output.ANSIColor;
import org.qualog.output.ANSIColorList;

public class ColorConfig {
    private final Map<String, ANSIColor> packageColors;
    private final Map<String, ANSIColor> classColors;
    private final Map<String, ANSIColor> methodColors;
    private final Map<String, ANSIColor> fileColors;
    private boolean useColor;

    public ColorConfig() {
        this(true);
    }
    
    public ColorConfig(boolean useColor) {
        packageColors = new HashMap<String, ANSIColor>();
        classColors = new HashMap<String, ANSIColor>();
        methodColors = new HashMap<String, ANSIColor>();
        fileColors = new HashMap<String, ANSIColor>();
        this.useColor = useColor;
    }

    public ANSIColor getMethodColor(String className, String methodName) {
        return methodColors.get(className + "#" + methodName);
    }

    public void setMethodColor(String className, String methodName, ANSIColor color) {
        methodColors.put(className + "#" + methodName, color);
    }

    public ANSIColor getPackageColor(String packageName) {
        return packageColors.get(packageName);
    }

    public void setPackageColor(String packageName, ANSIColor color) {
        packageColors.put(packageName, color);
    }

    public ANSIColor getClassColor(String className) {
        return classColors.get(className);
    }

    public void setClassColor(String className, ANSIColor color) {
        classColors.put(className, color);
    }

    public ANSIColor getFileColor(String fileName) {
        return fileColors.get(fileName);
    }    

    public void setFileColor(String fileName, ANSIColor color) {
        fileColors.put(fileName, color);
    }

    public boolean useColor() {
        return useColor;
    }

    public void setUseColor(boolean useColor) {
        this.useColor = useColor;
    }
}
