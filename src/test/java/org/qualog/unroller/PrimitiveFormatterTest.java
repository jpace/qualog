package org.qualog.unroller;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PrimitiveFormatterTest extends GeneratorTestCase {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromBoolean(StringArray expected, String key, boolean x) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createGenerator(result);
        new PrimitiveFormatter(sg).format(key, x);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromBoolean() {
        return paramsList(params(StringArray.of("abc: true"), "abc", true),
                          params(StringArray.of("abc: false"), "abc", false));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromByte(StringArray expected, String key, byte x) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createGenerator(result);
        new PrimitiveFormatter(sg).format(key, x);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromByte() {
        return paramsList(params(StringArray.of("abc: 1"), "abc", (byte)1),
                          params(StringArray.of("abc: 2"), "abc", (byte)2));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromChar(StringArray expected, String key, char x) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createGenerator(result);
        new PrimitiveFormatter(sg).format(key, x);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromChar() {
        return paramsList(params(StringArray.of("abc: x"), "abc", 'x'),
                          params(StringArray.of("abc: y"), "abc", 'y'));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromDouble(StringArray expected, String key, double x) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createGenerator(result);
        new PrimitiveFormatter(sg).format(key, x);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromDouble() {
        return paramsList(params(StringArray.of("abc: 1.2"), "abc", 1.2),
                          params(StringArray.of("abc: 3.4"), "abc", 3.4));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromFloat(StringArray expected, String key, float x) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createGenerator(result);
        new PrimitiveFormatter(sg).format(key, x);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromFloat() {
        return paramsList(params(StringArray.of("abc: 1.2"), "abc", 1.2F),
                          params(StringArray.of("abc: 3.4"), "abc", 3.4F));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromInt(StringArray expected, String key, int x) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createGenerator(result);
        new PrimitiveFormatter(sg).format(key, x);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromInt() {
        return paramsList(params(StringArray.of("abc: 1"), "abc", 1),
                          params(StringArray.of("abc: 2"), "abc", 2));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromLong(StringArray expected, String key, long x) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createGenerator(result);
        new PrimitiveFormatter(sg).format(key, x);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromLong() {
        return paramsList(params(StringArray.of("abc: 1"), "abc", 1L),
                          params(StringArray.of("abc: 2"), "abc", 2L));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromShort(StringArray expected, String key, short x) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createGenerator(result);
        new PrimitiveFormatter(sg).format(key, x);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromShort() {
        return paramsList(params(StringArray.of("abc: 1"), "abc", (short)1),
                          params(StringArray.of("abc: 2"), "abc", (short)2));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void withFormat(StringArray expected, String format, String key, String value) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createGenerator(format, result);
        new PrimitiveFormatter(sg).format(key, value);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForWithFormat() {
        return paramsList(params(StringArray.of("abc: def"), "%s: %s", "abc", "def"),
                          params(StringArray.of("<<abc>> {{def}}"), "<<%s>> {{%s}}", "abc", "def"));
    }    
}
