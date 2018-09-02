package org.qualog.types;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LogPrimitivesTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void isUndecorated(Boolean expected, Object obj) {
        Boolean result = new LogPrimitives().isUndecorated(obj);
        assertThat(result, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForIsUndecorated() {
        return paramsList(params(true, String.valueOf("")),
                          params(true, Boolean.TRUE),
                          params(true, Integer.valueOf(1)),
                          params(true, Double.valueOf(3.14)),
                          params(true, Character.valueOf('c')),
                          params(true, new StackTraceElement("", "", "", 0)),
                          params(false, new Object()));
    }
}
