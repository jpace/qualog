package org.qualog.unroller;

import java.util.List;
import java.util.regex.Pattern;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContextStringFormatterTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromKeyValue(String expected, String contextID, String key, String value) {
        ContextStringFormatter csf = new ContextStringFormatter(contextID);
        String result = csf.format(key, value);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromKeyValue() {
        return paramsList(params("c1 - abc: def",  "c1", "abc", "def"),
                          params("c1 - abc: null", "c1", "abc", null));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromMessage(String expected, String contextID, String msg) {
        ContextStringFormatter csf = new ContextStringFormatter(contextID);
        String result = csf.format(msg);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromMessage() {
        return paramsList(params("c1 - abc",  "c1", "abc"),
                          params("c1 - null", "c1", null));
    }
}
