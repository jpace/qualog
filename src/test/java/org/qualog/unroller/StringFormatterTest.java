package org.qualog.unroller;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StringFormatterTest extends Parameterized {
    public class TestStringFormatter extends StringFormatter {
        private String line;

        public TestStringFormatter() {
        }

        public TestStringFormatter(String format) {
            super(format);
        }

        public void write(String line) {
            this.line = line;
        }

        public String getLine() {
            return line;
        }
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromKeyValue(String expected, String key, String value) {
        TestStringFormatter tsf = new TestStringFormatter();
        tsf.format(key, value);
        String result = tsf.getLine();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromKeyValue() {
        return paramsList(params("abc: def", "abc", "def"),
                          params("abc: null", "abc", null));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromMsg(String expected, String msg) {
        TestStringFormatter tsf = new TestStringFormatter();
        tsf.format(msg);
        String result = tsf.getLine();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromMsg() {
        return paramsList(params("abc", "abc"),
                          params("def", "def"),
                          params("null", null));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void formatNull(String expected, String key) {
        TestStringFormatter tsf = new TestStringFormatter();
        tsf.formatNull(key);
        String result = tsf.getLine();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFormatNull() {
        return paramsList(params("abc: null", "abc"),
                          params("def: null", "def"),
                          params("null: null", null));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void withFormat(String expected, String format, String key, String value) {
        TestStringFormatter tsf = new TestStringFormatter(format);
        tsf.format(key, value);
        String result = tsf.getLine();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForWithFormat() {
        return paramsList(params("abc: def", "%s: %s", "abc", "def"),
                          params("<<abc>> {{def}}", "<<%s>> {{%s}}", "abc", "def"));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void withNullFormat(String expected, String key, String value) {
        TestStringFormatter tsf = new TestStringFormatter(null);
        tsf.format(key, value);
        String result = tsf.getLine();
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForWithNullFormat() {
        return paramsList(params("abc: def", "abc", "def"));
    }    
}
