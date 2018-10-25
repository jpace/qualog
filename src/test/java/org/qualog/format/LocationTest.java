package org.qualog.format;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LocationTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void init(String expFileName, Integer expLineNumber, String expClassName, String expMethodName, 
                     String fileName,    Integer lineNumber,    String className,    String methodName) {
        Location obj = new Location(fileName, lineNumber, className, methodName);
        assertThat(obj.getFileName(),   equalTo(expFileName));
        assertThat(obj.getLineNumber(), equalTo(expLineNumber));
        assertThat(obj.getClassName(),  equalTo(expClassName));
        assertThat(obj.getMethodName(), equalTo(expMethodName));
    }
    
    private List<Object[]> parametersForInit() {
        return paramsList(params("abc", 1, "def", "ghi", "abc", 1, "def", "ghi"),
                          params("xyz", 3, "jkl", "mno", "xyz", 3, "jkl", "mno"));
    }
}
