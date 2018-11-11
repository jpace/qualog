package org.qualog.format;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ClassUtilTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void getShortClassName(String expected, String clsName) {
        ClassUtil cu = new ClassUtil();
        String result = cu.getShortName(clsName);
        assertThat(result, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForGetShortClassName() {
        // (String fileName, Integer lineNumber, String className, String methodName)
        return paramsList(params("Abc",     "Abc"),     
                          params("o.Abc",   "org.Abc"), 
                          params("o.x.Abc", "org.xyz.Abc"));
    }    
}
