package org.qualog.filter;

import org.incava.ijdk.collect.StringArray;

/**
 * Contains lists of classes, methods, and packages that should be omitted from logged elements.
 */
public class StackFilter {
    private final StringArray packagesSkipped;
    private final StringArray classesSkipped;
    private final StringArray methodsSkipped;

    public StackFilter() {
        this(StringArray.of("org.qualog"), StringArray.of("tr.Ace"), StringArray.empty());
    }

    public StackFilter(StringArray packagesSkipped, StringArray classesSkipped, StringArray methodsSkipped) {
        this.packagesSkipped = packagesSkipped;
        this.classesSkipped = classesSkipped;
        this.methodsSkipped = methodsSkipped;
    }
    
    public void addPackageSkipped(String pkgName) {
        packagesSkipped.add(pkgName);
    }
    
    public void addClassSkipped(String clsName) {
        classesSkipped.add(clsName);
    }

    public void addMethodSkipped(String methodName) {
        methodsSkipped.add(methodName);
    }

    public boolean isClassSkipped(String clsName) {
        return classesSkipped.contains(clsName);
    }

    public boolean isMethodSkipped(String methodName) {
        return methodsSkipped.contains(methodName);
    }

    public boolean isPackageSkipped(String clsName) {
        for (String pkgName : packagesSkipped) {
            if (clsName.startsWith(pkgName + ".")) {
                return true;
            }
        }
        return false;
    }

    public boolean isSkipped(String clsName, String methodName) {
        return isClassSkipped(clsName) || isPackageSkipped(clsName) || isMethodSkipped(methodName);
    }
}
