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
        // (String fileName, Integer lineNumber, String className, String methodName)
        return paramsList(params(null, null),
                          params("[FileAbc 7] {ClsAbc#methDef}",     new Location("FileAbc.java",  7,  "ClsAbc",  "methDef")),
                          params("[FileAbc 7] {ClsAbc#methDef}",     new Location("FileAbc.java",  7,  "ClsAbc",  "methDef")));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void formatSpecified(String expected, Location loc, String format) {
        LocationFormatter lf = new LocationFormatter(format);
        String result = lf.format(loc);
        assertThat(result, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForFormatSpecified() {
        return paramsList(params(null, null, null),
                          params("FAbc       #    7:   ClsDef.methGhi",  new Location("FAbc.java",  7,  "ClsDef",  "methGhi"), "%-10.10s #%5d: %8s.%s"),
                          params("      FAbc;    7,     ClsDef#   methGhi",  new Location("FAbc.java",  7,  "ClsDef",  "methGhi"), "%10.10s; %4d, %10s#%10s"));
    }

    @Test @Parameters @TestCaseName("{method}(...) #{index} [{params}]")
    public void getShortClassName(String expected, String clsName) {
        LocationFormatter lf = new LocationFormatter();
        String result = lf.getShortClassName(clsName);
        assertThat(result, equalTo(expected));
    }
    
    private java.util.List<Object[]> parametersForGetShortClassName() {
        // (String fileName, Integer lineNumber, String className, String methodName)
        return paramsList(params("Abc",     "Abc"),     
                          params("o.Abc",   "org.Abc"), 
                          params("o.x.Abc", "org.xyz.Abc"));
    }

    
}
