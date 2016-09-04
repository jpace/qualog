package org.qualog.output;

import java.util.List;
import static org.incava.ijdk.util.IUtil.list;

/**
 * Contains lists of classes, methods, and packages that should be omitted from logged elements.
 */
public class OmittedFilter {
    private List<String> packagesSkipped;
    private List<String> classesSkipped;
    private List<String> methodsSkipped;
    
    public OmittedFilter() {
        this.packagesSkipped = list("org.qualog", "org.incava.qualog", "org.qualog");
        this.classesSkipped = list("tr.Ace");
        this.methodsSkipped = list("log");
    }

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
