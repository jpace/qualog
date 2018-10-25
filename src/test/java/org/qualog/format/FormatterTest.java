package org.qualog.format;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormatterTest extends Parameterized {
    @Test
    public void init() {
        Formatter sf = new Formatter();
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void formatWithoutSnip(String expected, String str, int width, boolean leftJustify) {
        Formatter sf = new Formatter();
        String result = sf.format(str, width, leftJustify);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFormatWithoutSnip() {
        return paramsList(params("abc",  "abc", 3, true), 
                          params("ab",   "abc", 2, true), 
                          params("abc ", "abc", 4, true), 
                          params(" abc", "abc", 4, false));
    }    

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void formatWithSnip(String expected, String str, int width, boolean leftJustify, String snipStr) {
        Formatter sf = new Formatter();
        String result = sf.format(str, width, leftJustify, snipStr);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForFormatWithSnip() {
        return paramsList(params("abc",  "abc",   3, true,  "-"),  
                          params("a-",   "abc",   2, true,  "-"),  
                          params("a..",  "abcd",  3, true,  ".."), 
                          params("abcd", "abcd",  4, true,  ".."), 
                          params("abc ", "abc",   4, true,  "-"),  
                          params("abc-", "abcde", 4, true,  "-"),  
                          params("abcd", "abcde", 4, true,  null),  
                          params(" abc", "abc",   4, false, "-"));
    }    
}
