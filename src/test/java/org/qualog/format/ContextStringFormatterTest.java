package org.qualog.format;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContextStringFormatterTest extends Parameterized {
    // public class CustomFormatter implements StringFormatter {
    //     private final String str;

    //     public CustomFormatter(String str) {
    //         this.str = str;
    //     }

    //     public String format(String key, String value) {
    //         return str + ", " + key + " => " + value;
    //     }
    
    //     public String format(String msg) {
    //         return str + ", " + msg;
    //     }
    // }
    
    // @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    // public <T> void fromKeyValue(String expected, String contextID, String key, String value) {
    //     ContextStringFormatter csf = new ContextStringFormatter(contextID);
    //     String result = csf.format(key, value);
    //     assertThat(result, equalTo(expected));
    // }
    
    // private List<Object[]> parametersForFromKeyValue() {
    //     return paramsList(params("c1 - abc: def",  "c1", "abc", "def"),
    //                       params("c1 - abc: null", "c1", "abc", null));
    // }

    // @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    // public <T> void fromMessage(String expected, String contextID, String msg) {
    //     ContextStringFormatter csf = new ContextStringFormatter(contextID);
    //     String result = csf.format(msg);
    //     assertThat(result, equalTo(expected));
    // }
    
    // private List<Object[]> parametersForFromMessage() {
    //     return paramsList(params("c1 - abc",  "c1", "abc"),
    //                       params("c1 - null", "c1", null));
    // }

    // @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    // public <T> void withFormatterKeyValue(String expected, String contextID, String str, String key, String value) {
    //     ContextStringFormatter csf = new ContextStringFormatter(contextID, ContextStringFormatter.DEFAULT_FORMAT, new CustomFormatter(str));
    //     String result = csf.format(key, value);
    //     assertThat(result, equalTo(expected));
    // }
    
    // private List<Object[]> parametersForWithFormatterKeyValue() {
    //     return paramsList(params("c1 - s1, k1 => v1", "c1", "s1", "k1", "v1"),
    //                       params("c1 - s1, k2 => v2", "c1", "s1", "k2", "v2"));
    // }

    // @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    // public <T> void withFormatterMessage(String expected, String contextID, String str, String msg) {
    //     ContextStringFormatter csf = new ContextStringFormatter(contextID, ContextStringFormatter.DEFAULT_FORMAT, new CustomFormatter(str));
    //     String result = csf.format(msg);
    //     assertThat(result, equalTo(expected));
    // }
    
    // private List<Object[]> parametersForWithFormatterMessage() {
    //     return paramsList(params("c1 - s1, abc",  "c1", "s1", "abc"),
    //                       params("c1 - s1, null", "c1", "s1", null));
    // }
}
