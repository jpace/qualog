package org.qualog;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;
import org.qualog.writer.LogWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LogsTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void hasWriter(boolean expected, Logs logs, String name) {
        boolean result = logs.hasWriter(name);
        assertThat(result, equalTo(expected));
    }
    
    private List<Object[]> parametersForHasWriter() {
        Logs logs = new Logs();
        return paramsList(params(false, logs, "bogus"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void getWriter(boolean expected, Logs logs, String name) {
        LogWriter result = logs.getWriter(name);
        assertThat(result != null, equalTo(expected));
    }
    
    private List<Object[]> parametersForGetWriter() {
        Logs logs = new Logs();
        return paramsList(params(true, logs, "bogus"),
                          params(true, logs, null),
                          params(true, logs, "bogus"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void log(boolean expected, Logs logs, String name) {
        LogWriter result = logs.getWriter(name);
        // assertThat(result != null, equalTo(expected));
    }
    
    private List<Object[]> parametersForLog() {
        Logs logs = new Logs();
        LogWriter x = logs.getWriter("x");
        LogWriter y = logs.getWriter("y");
        LogWriter z = logs.getWriter("z");

        x.log("x-1", "huh");
        y.log("2-y");
        z.log("z3");
        
        return paramsList(params(true, logs, "x"),
                          params(true, logs, "y"),
                          params(true, logs, "z"));
    }
    
}
