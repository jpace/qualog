package org.qualog.format;

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
    // this exists in the 2.0 version of hamcrest ...
    private void assertMatchesPattern(String expected, String result) {
        Pattern pattern = Pattern.compile(expected);
        boolean matches = pattern.matcher(result).matches();
        assertThat(matches, withContext(message("expected", expected, "result", result), equalTo(true)));
    }    
    
    private void assertMatchesDefault(String expected, String result) {
        assertMatchesPattern("\\d{4}\\-\\d{2}\\-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}[\\-\\+]\\d{2}:\\d{2} " + expected, result);
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromKeyValue(String expected, String key, String value) {
        TimestampStringFormatter tsf = new TimestampStringFormatter();
        String result = tsf.format(key, value);
        assertMatchesDefault(expected, result);
    }
    
    private List<Object[]> parametersForFromKeyValue() {
        return paramsList(params("abc: def", "abc", "def"),
                          params("abc: null", "abc", null));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void fromMessage(String expected, String msg) {
        TimestampStringFormatter tsf = new TimestampStringFormatter();
        String result = tsf.format(msg);
        assertMatchesDefault(expected, result);
    }
    
    private List<Object[]> parametersForFromMessage() {
        return paramsList(params("abc", "abc"),
                          params("null", null));
    }
    
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void withDateFormat(String expected, String key, String value) {
        String dateFormat = "yy-MMM-uu, KK a mm";
        TimestampStringFormatter tsf = new TimestampStringFormatter(new MessageFormatter(), null, dateFormat);
        String result = tsf.format(key, value);
        assertMatchesPattern("\\d{2}\\-\\w{3}\\-\\d{2}, \\d{2} [AP]M \\d{2} " + expected, result);
    }
    
    private List<Object[]> parametersForWithDateFormat() {
        return paramsList(params("abc: def", "abc", "def"),
                          params("abc: null", "abc", null));
    }    
}
