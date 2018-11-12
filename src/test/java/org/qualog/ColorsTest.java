package org.qualog;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ColorsTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void toString(String expected, String result) {
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForToString() {
        String esc = "" + (char)27 + "[";
        
        return paramsList(params(esc + "0mabc" + esc + "0m", Colors.none("abc")),
                          params(esc + "0mdef" + esc + "0m", Colors.none("def")),
                          params(esc + "0mabc" + esc + "0m", Colors.reset("abc")),
                          params(esc + "1mabc" + esc + "0m", Colors.bold("abc")),
                          params(esc + "1m" + esc + "33mabc" + esc + "0m" + esc + "0m", Colors.bold(Colors.yellow("abc"))));
    }    
}

