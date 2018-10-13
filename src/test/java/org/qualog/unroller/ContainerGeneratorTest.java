package org.qualog.unroller;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContainerGeneratorTest extends GeneratorTestCase {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromEmpty(StringArray expected, String key) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createGenerator(result);
        new ContainerGenerator(sg).generateEmpty(key);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromEmpty() {
        return paramsList(params(StringArray.of("abc: ()"), "abc"),
                          params(StringArray.of("def: ()"), "def"),
                          params(StringArray.of("null: ()"), null));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void getLimitDefault(int expected, int size) {
        int result = new ContainerGenerator(null).getLimit(size);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForGetLimitDefault() {
        return paramsList(params(0, 0),
                          params(1, 1),
                          params(2, 2));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void getLimitSpecified(int expected, int limit, int size) {
        int result = new ContainerGenerator(null, limit).getLimit(size);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForGetLimitSpecified() {
        return paramsList(params(0, 0, 0),
                          params(0, 0, 1),
                          params(0, 1, 0),
                          params(1, 1, 1),
                          params(1, 2, 1));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void withinLimit(boolean expected, ContainerGenerator fmtr, int idx) {
        boolean result = fmtr.withinLimit(idx);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForWithinLimit() {
        ContainerGenerator x = new ContainerGenerator(null);
        ContainerGenerator y = new ContainerGenerator(null, 3);
        
        return paramsList(params(true,  x, 0), 
                          params(true,  x, 4), 
                          params(true,  y, 0), 
                          params(true,  y, 2), 
                          params(false, y, 3));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void checkNull(boolean expected, String key, Object value) {
        StringArray lines = StringArray.empty();
        StringGenerator sg = createGenerator(lines);
        boolean result = new ContainerGenerator(sg).checkNull(key, value);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForCheckNull() {
        return paramsList(params(true,  "abc", null), 
                          params(false, "abc", "x"));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void checkEmptyInt(boolean expected, String key, int length) {
        StringArray lines = StringArray.empty();
        StringGenerator sg = createGenerator(lines);
        boolean result = new ContainerGenerator(sg).checkEmpty(key, length);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForCheckEmptyInt() {
        return paramsList(params(true,  "abc", 0), 
                          params(false, "abc", 1));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void checkEmptyBoolean(boolean expected, String key, boolean condition) {
        StringArray lines = StringArray.empty();
        StringGenerator sg = createGenerator(lines);
        boolean result = new ContainerGenerator(sg).checkEmpty(key, condition);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForCheckEmptyBoolean() {
        return paramsList(params(true,  "abc", true), 
                          params(false, "abc", false));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void withFormat(StringArray expected, String format, String key, String value) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createGenerator(format, result);
        new ContainerGenerator(sg).format(key, value);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForWithFormat() {
        return paramsList(params(StringArray.of("abc: def"), "%s: %s", "abc", "def"),
                          params(StringArray.of("<<abc>> {{def}}"), "<<%s>> {{%s}}", "abc", "def"));
    }    
}
