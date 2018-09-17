package org.qualog.unroller;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StringFormatterTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromKeyValue(String expected, String key, String value) {
        LineStringFormatter lsf = new LineStringFormatter();
        lsf.format(key, value);
        String result = lsf.getLine();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromKeyValue() {
        return paramsList(params("abc: def", "abc", "def"),
                          params("abc: null", "abc", null));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromMsg(String expected, String msg) {
        LineStringFormatter lsf = new LineStringFormatter();
        lsf.format(msg);
        String result = lsf.getLine();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromMsg() {
        return paramsList(params("abc", "abc"),
                          params("def", "def"),
                          params("null", null));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void formatNull(String expected, String key) {
        LineStringFormatter lsf = new LineStringFormatter();
        lsf.formatNull(key);
        String result = lsf.getLine();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFormatNull() {
        return paramsList(params("abc: null", "abc"),
                          params("def: null", "def"),
                          params("null: null", null));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void withFormat(String expected, String format, String key, String value) {
        LineStringFormatter lsf = new LineStringFormatter(format);
        lsf.format(key, value);
        String result = lsf.getLine();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForWithFormat() {
        return paramsList(params("abc: def", "%s: %s", "abc", "def"),
                          params("<<abc>> {{def}}", "<<%s>> {{%s}}", "abc", "def"));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void withNullFormat(String expected, String key, String value) {
        LineStringFormatter lsf = new LineStringFormatter(null);
        lsf.format(key, value);
        String result = lsf.getLine();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForWithNullFormat() {
        return paramsList(params("abc: def", "abc", "def"));
    }    
}
