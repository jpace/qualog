package org.qualog.types;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StringFormatterTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromKeyValue(StringArray expected, String key, String value) {
        StringArray result = StringArray.empty();
        new StringFormatter(result).format(key, value);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromKeyValue() {
        return paramsList(params(StringArray.of("abc: def"), "abc", "def"),
                          params(StringArray.of("abc: null"), "abc", null));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromMsg(StringArray expected, String msg) {
        StringArray result = StringArray.empty();
        new StringFormatter(result).format(msg);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromMsg() {
        return paramsList(params(StringArray.of("abc"), "abc"),
                          params(StringArray.of("def"), "def"),
                          params(StringArray.of("null"), null));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void formatNull(StringArray expected, String key) {
        StringArray result = StringArray.empty();
        new StringFormatter(result).formatNull(key);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFormatNull() {
        return paramsList(params(StringArray.of("abc: null"), "abc"),
                          params(StringArray.of("def: null"), "def"),
                          params(StringArray.of("null: null"), null));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void withFormat(StringArray expected, String format, String key, String value) {
        StringArray result = StringArray.empty();
        new StringFormatter(format, result).format(key, value);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForWithFormat() {
        return paramsList(params(StringArray.of("abc: def"), "%s: %s", "abc", "def"),
                          params(StringArray.of("<<abc>> {{def}}"), "<<%s>> {{%s}}", "abc", "def"));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void withNullFormat(StringArray expected, String key, String value) {
        StringArray result = StringArray.empty();
        new StringFormatter(null, result).format(key, value);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForWithNullFormat() {
        return paramsList(params(StringArray.of("abc: def"), "abc", "def"));
    }    
}
