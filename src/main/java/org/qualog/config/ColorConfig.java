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
        return getColor(methodColors, className + "#" + methodName);
    }

    public void setMethodColor(String className, String methodName, ANSIColor color) {
        setColor(methodColors, className + "#" + methodName, color);
    }

    public ANSIColor getPackageColor(String packageName) {
        return getColor(packageColors, packageName);
    }

    public void setPackageColor(String packageName, ANSIColor color) {
        setColor(packageColors, packageName, color);
    }

    public ANSIColor getClassColor(String className) {
        return getColor(classColors, className);
    }

    public void setClassColor(String className, ANSIColor color) {
        setColor(classColors, className, color);
    }

    public ANSIColor getFileColor(String fileName) {
        return getColor(fileColors, fileName);
    }    

    public void setFileColor(String fileName, ANSIColor color) {
        setColor(fileColors, fileName, color);
    }

    public boolean useColor() {
        return useColor;
    }

    private ANSIColor getColor(Map<String, ANSIColor> colors, String key) {
        return colors.get(key);
    }    

    private void setColor(Map<String, ANSIColor> colors, String key, ANSIColor color) {
        colors.put(key, color);
    }
}
