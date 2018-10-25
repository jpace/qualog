package org.qualog.format;

import java.util.List;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LocationFormatterTest extends Parameterized {
    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void formatDefault(String expected, Location loc) {
        LocationFormatter lf = new LocationFormatter();
        String result = lf.format(loc);
        assertThat(result, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForFormatDefault() {
        return paramsList(params(null, null),
                          params("[fn1 7] {cn2#mn3}",     new Location("fn1",  7,  "cn2",  "mn3")), 
                          params("[bc11 37] {de22#fg33}", new Location("bc11", 37, "de22", "fg33")));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void formatSpecified(String expected, Location loc) {
        LocationFormatter lf = new LocationFormatter("%-10s %5d: %8s.%s");
        String result = lf.format(loc);
        assertThat(result, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForFormatSpecified() {
        return paramsList(params(null, null),
                          params("fn1            7:      cn2.mn3",  new Location("fn1",  7,  "cn2",  "mn3")), 
                          params("bc11          37:     de22.fg33", new Location("bc11", 37, "de22", "fg33")));
    }
}
