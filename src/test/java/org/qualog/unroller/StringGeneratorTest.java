package org.qualog.unroller;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StringGeneratorTest extends GeneratorTestCase {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void fromKeyValue(StringArray expected, String key, String value) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createGenerator(result);
        sg.generate(key, value);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFromKeyValue() {
        return paramsList(params(StringArray.of("abc: 123"), "abc", "123"),
                          params(StringArray.of("def: 456"), "def", "456"));
    }

}
