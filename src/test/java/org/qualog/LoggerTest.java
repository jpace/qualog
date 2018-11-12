package org.qualog;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.incava.ijdk.collect.Array;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.incava.attest.Assertions.message;

public class LoggerTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void log(boolean expected, Logger log, String key, Object value) {
        log.log(key, value);
        // assertThat(result != null, equalTo(expected));
    }
    
    private List<Object[]> parametersForLog() {
        Logs logs = new Logs();
        Logger x = logs.getLogger("x");
        x.setVerbose(true);
        Logger y = logs.getLogger("y");
        y.setVerbose(false);
        Logger z = logs.getLogger("z");
        
        return paramsList(params(true, x, "kx", "vx"),
                          params(true, y, "ky", "vy"),
                          params(true, z, "kz", "vz"));
    }    
}
