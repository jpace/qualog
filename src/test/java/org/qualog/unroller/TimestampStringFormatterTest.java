package org.qualog.unroller;

import java.util.List;
import java.util.regex.Pattern;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;

public class TimestampStringFormatterTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromKeyValue(String expected, String key, String value) {
        Pattern pattern = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}[\\-\\+]\\d{2}:\\d{2} " + expected);
        TimestampStringFormatter tsf = new TimestampStringFormatter();
        String result = tsf.format(key, value);
        boolean matches = pattern.matcher(result).matches();
        assertThat(matches, withContext(message("result", result), equalTo(true)));
    }
    
    private List<Object[]> parametersForFromKeyValue() {
        return paramsList(params("abc: def", "abc", "def"),
                          params("abc: null", "abc", null));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromMessage(String expected, String msg) {
        Pattern pattern = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}[\\-\\+]\\d{2}:\\d{2} " + expected);
        TimestampStringFormatter tsf = new TimestampStringFormatter();
        String result = tsf.format(msg);
        boolean matches = pattern.matcher(result).matches();
        assertThat(matches, withContext(message("result", result, "pattern", pattern), equalTo(true)));
    }
    
    private List<Object[]> parametersForFromMessage() {
        return paramsList(params("abc", "abc"),
                          params("null", null));
    }
}
