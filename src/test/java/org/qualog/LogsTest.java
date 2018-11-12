package org.qualog;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;
import org.qualog.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LogsTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void hasLogger(boolean expected, Logs logs, String name) {
        boolean result = logs.hasLogger(name);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForHasLogger() {
        Logs logs = new Logs();
        return paramsList(params(false, logs, "bogus"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void getLogger(boolean expected, Logs logs, String name) {
        Logger result = logs.getLogger(name);
        assertThat(result != null, equalTo(expected));
    }
    
    private List<Object[]> parametersForGetLogger() {
        Logs logs = new Logs();
        return paramsList(params(true, logs, "bogus"),
                          params(true, logs, null),
                          params(true, logs, "bogus"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void log(boolean expected, Logs logs, String name) {
        Logger result = logs.getLogger(name);
        // assertThat(result != null, equalTo(expected));
    }
    
    private List<Object[]> parametersForLog() {
        Logs logs = new Logs();
        Logger x = logs.getLogger("x");
        Logger y = logs.getLogger("y");
        Logger z = logs.getLogger("z");

        x.log("x-1", "huh");
        y.log("2-y");
        z.log("z3");
        
        return paramsList(params(true, logs, "x"),
                          params(true, logs, "y"),
                          params(true, logs, "z"));
    }    
}
