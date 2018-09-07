package org.qualog.types;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LogObjectArrayTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void lines(StringArray expected, String name, Object[] ary) {
        ElementParams params = new ElementParams(null, null, name, -1);
        LogObjectArray le = new LogObjectArray(params, ary);
        StringArray result = le.lines();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForLines() {
        return paramsList(params(StringArray.of("abc: ()"), "abc", new String[] { }),
                          params(StringArray.of("abc[0]: d"), "abc", new String[] { "d" }),
                          params(StringArray.of("def[0]: d"), "def", new String[] { "d" }),
                          params(StringArray.of("abc[0]: d", "abc[1]: e"), "abc", new String[] { "d", "e" }));
    }
}
