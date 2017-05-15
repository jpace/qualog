package org.qualog.output;

import java.util.List;

import static org.incava.ijdk.lang.Common.list;

/**
 * Contains lists of classes, methods, and packages that should be omitted from logged elements.
 */
public class OmittedFilter {
    private final List<String> packagesSkipped = list("org.qualog", "org.incava.qualog", "org.qualog");
    private final List<String> classesSkipped = list("tr.Ace");
    private final List<String> methodsSkipped = list("log");
    
    public void addClassSkipped(Class<?> cls) {
        addClassSkipped(cls.getName());
    }
    
    public void addClassSkipped(String clsName) {
        classesSkipped.add(clsName);
    }

    public void addPackageSkipped(String pkgName) {
        packagesSkipped.add(pkgName);
    }

    public boolean isSkipped(StackTraceElement ste) {
        String className = ste.getClassName();
        if (classesSkipped.contains(className) || methodsSkipped.contains(ste.getMethodName())) {
            return true;
        }
        
        for (String pkgName : packagesSkipped) {
            if (className.startsWith(pkgName + ".")) {
                return true;
            }
        }
        return false;
    }
}
