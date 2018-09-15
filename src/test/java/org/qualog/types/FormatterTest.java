package org.qualog.types;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormatterTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void format(StringArray expected, String key, Object obj) {
        StringArray result = StringArray.empty();
        new Formatter(result).format(key, obj);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFormat() {
        return paramsList(params(StringArray.of("abc: def"), "abc", "def"),
                          params(StringArray.of("abc: null"), "abc", null));
    }
}
