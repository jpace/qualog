package org.qualog.unroller;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MessageFormatterTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromKeyValue(String expected, String key, String value) {
        MessageFormatter sf = new MessageFormatter();
        String result = sf.format(key, value);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromKeyValue() {
        return paramsList(params("abc: def", "abc", "def"),
                          params("abc: null", "abc", null));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromMsg(String expected, String msg) {
        MessageFormatter sf = new MessageFormatter();
        String result = sf.format(msg);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromMsg() {
        return paramsList(params("abc", "abc"),
                          params("def", "def"),
                          params("null", null));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void withKeyValueFormat(String expected, String keyValueFormat, String key, String value) {
        MessageFormatter sf = new MessageFormatter(keyValueFormat, null);
        String result = sf.format(key, value);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForWithKeyValueFormat() {
        return paramsList(params("abc: def", "%s: %s", "abc", "def"),
                          params("<<abc>> {{def}}", "<<%s>> {{%s}}", "abc", "def"));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void withNullFormats(String expected, String key, String value) {
        MessageFormatter sf = new MessageFormatter(null, null);
        String result = sf.format(key, value);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForWithNullFormats() {
        return paramsList(params("abc: def", "abc", "def"));
    }    
}
