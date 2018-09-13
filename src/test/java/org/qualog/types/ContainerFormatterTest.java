package org.qualog.types;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContainerFormatterTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromEmpty(StringArray expected, String key) {
        StringArray result = StringArray.empty();
        new ContainerFormatter(result).formatEmpty(key);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromEmpty() {
        return paramsList(params(StringArray.of("abc: ()"), "abc"),
                          params(StringArray.of("def: ()"), "def"),
                          params(StringArray.of("null: ()"), null));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void getLimitDefault(int expected, int size) {
        int result = new ContainerFormatter().getLimit(size);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForGetLimitDefault() {
        return paramsList(params(0, 0),
                          params(1, 1),
                          params(2, 2));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void getLimitSpecified(int expected, int limit, int size) {
        int result = new ContainerFormatter(limit).getLimit(size);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForGetLimitSpecified() {
        return paramsList(params(0, 0, 0),
                          params(0, 0, 1),
                          params(0, 1, 0),
                          params(1, 1, 1),
                          params(1, 2, 1));
    }
}
