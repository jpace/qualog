package org.qualog.types;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LogExceptionTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void lines(StringArray expected, Throwable thr, Integer numFrames) {
        ElementParams params = new ElementParams(null, null, "def", numFrames);
        LogException le = new LogException(params, thr);
        StringArray result = le.lines(numFrames);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLines() {
        Throwable t = new Exception("abc");
        
        return paramsList(params(StringArray.of("java.lang.Exception: abc"), t, 0),
                          params(StringArray.of("java.lang.Exception: abc",
                                                "def[0]: org.qualog.types.LogExceptionTest.parametersForLines(LogExceptionTest.java:23)"), t, 1),
                          params(StringArray.of("java.lang.Exception: abc",
                                                "def[0]: org.qualog.types.LogExceptionTest.parametersForLines(LogExceptionTest.java:23)",
                                                "def[1]: sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)"), t, 2));
    }
}
