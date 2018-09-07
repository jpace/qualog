package org.qualog.types;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ObjectTypesTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void toStringTest(String expected, Object obj) {
        String result = new ObjectTypes().toString(obj);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForToStringTest() {
        StringBuilder sb = new StringBuilder();
        sb.append("xyz");
        String hashCode = Integer.toHexString(sb.hashCode());
        return paramsList(params("def", "def"),
                          params("null", null),
                          params("xyz (java.lang.StringBuilder) #" + hashCode, sb));
    }
}
