package org.qualog.types;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LogObjectMessageTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void testToString(String expected, Object arg) {
        LogObjectMessage le = new LogObjectMessage(arg);
        String result = le.toString();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForTestToString() {
        StringBuilder sb = new StringBuilder("abc");
        String expStr = "abc (java.lang.StringBuilder) #" + Integer.toHexString(sb.hashCode());
        return paramsList(params("abc", "abc"),
                          params("null", null),
                          params(expStr, sb));
    }
}
